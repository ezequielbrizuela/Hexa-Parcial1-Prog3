package com.hexa.service;

import com.hexa.models.EstacionAnclaje;
import com.hexa.models.Vehiculo;
import com.hexa.dto.DesbloqueoRequest;
import com.hexa.dto.DesbloqueoResponse;
import com.hexa.exceptions.BateriaInsuficienteException;
import com.hexa.exceptions.UsuarioNoEncontradoException;
import com.hexa.exceptions.VehiculoNoEncontradoException;
import com.hexa.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EcoRideService {

    private static final int BATERIA_MINIMA = 15;

    private final EstacionAnclaje estacion;
    private final Map<Long, Usuario> usuarios;
    private final ProcesadorPagoFactory procesadorPagoFactory;

    public EcoRideService(EstacionAnclaje estacion,
                          Map<Long, Usuario> usuarios,
                          ProcesadorPagoFactory procesadorPagoFactory) {
        this.estacion = estacion;
        this.usuarios = new HashMap<>(usuarios);
        this.procesadorPagoFactory = procesadorPagoFactory;
    }

    public DesbloqueoResponse desbloquear(DesbloqueoRequest request) {
        Usuario usuario = buscarUsuario(request.getIdUsuario());
        Vehiculo vehiculo = buscarVehiculo(request.getPatente());

        validarBateria(vehiculo);

        double importeFinal = usuario.aplicarDescuento(vehiculo.getTarifaBase());

        ProcesadorPago procesadorPago = procesadorPagoFactory.obtenerProcesador(request.getMetodoPago());
        procesadorPago.cobrar(importeFinal);

        estacion.quitarVehiculo(vehiculo);

        String mensaje = "Desbloqueo exitoso. Vehiculo liberado y cobro realizado correctamente.";
        return new DesbloqueoResponse(mensaje, vehiculo.getPatente(), importeFinal, procesadorPago.getMetodoPago().name());
    }

    private Usuario buscarUsuario(Long idUsuario) {
        Usuario usuario = usuarios.get(idUsuario);
        if (usuario == null) {
            throw new UsuarioNoEncontradoException("No existe un usuario registrado con ID " + idUsuario + ".");
        }
        return usuario;
    }

    private Vehiculo buscarVehiculo(String patente) {
        Vehiculo vehiculo = estacion.buscarVehiculoPorPatente(patente);
        if (vehiculo == null) {
            throw new VehiculoNoEncontradoException("Vehiculo No Encontrado: la patente " + patente + " no figura en la estacion.");
        }
        return vehiculo;
    }

    private void validarBateria(Vehiculo vehiculo) {
        if (vehiculo.getBateria() < BATERIA_MINIMA) {
            throw new BateriaInsuficienteException("Bateria Insuficiente: el vehiculo tiene " + vehiculo.getBateria() + "% de bateria.");
        }
    }
}
