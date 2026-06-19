package com.hexa.service;

import com.hexa.dto.CambioTarifaRequest;
import com.hexa.dto.CambioTarifaResponse;
import com.hexa.dto.DesbloqueoRequest;
import com.hexa.dto.DesbloqueoResponse;
import com.hexa.dto.FinalizarAlquilerRequest;
import com.hexa.dto.FinalizarAlquilerResponse;
import com.hexa.exceptions.BateriaInsuficienteException;
import com.hexa.exceptions.SolicitudInvalidaException;
import com.hexa.exceptions.UsuarioNoEncontradoException;
import com.hexa.exceptions.VehiculoNoEncontradoException;
import com.hexa.models.BicicletaElectrica;
import com.hexa.models.EstacionAnclaje;
import com.hexa.models.Monopatin;
import com.hexa.models.Usuario;
import com.hexa.models.UsuarioPremium;
import com.hexa.models.UsuarioRegular;
import com.hexa.models.Vehiculo;
import com.hexa.service.tarifa.EstrategiaTarifa;
import com.hexa.service.tarifa.EstrategiaTarifaFactory;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EcoRideService {

    private static final int BATERIA_MINIMA = 15;

    private final EstacionAnclaje estacion;
    private final Map<Long, Usuario> usuarios;
    private final ProcesadorPagoFactory procesadorPagoFactory;
    private final EstrategiaTarifaFactory estrategiaTarifaFactory;
    private EstrategiaTarifa estrategiaTarifaActiva;

    public EcoRideService(ProcesadorPagoFactory procesadorPagoFactory, EstrategiaTarifaFactory estrategiaTarifaFactory) {
        this.estacion = crearEstacionInicial();
        this.usuarios = crearUsuariosIniciales();
        this.procesadorPagoFactory = procesadorPagoFactory;
        this.estrategiaTarifaFactory = estrategiaTarifaFactory;
        this.estrategiaTarifaActiva = estrategiaTarifaFactory.obtenerEstrategia("ESTANDAR");
    }

    public DesbloqueoResponse desbloquear(DesbloqueoRequest request) {
        validarDesbloqueoRequest(request);
        Usuario usuario = buscarUsuario(request.getIdUsuario());
        Vehiculo vehiculo = buscarVehiculo(request.getPatente());

        validarBateria(vehiculo);

        ProcesadorPago procesadorPago = procesadorPagoFactory.obtenerProcesador(request.getMetodoPago());
        vehiculo.iniciarViaje();

        String mensaje = "Desbloqueo exitoso. Vehiculo liberado correctamente.";
        return new DesbloqueoResponse(
                mensaje,
                vehiculo.getPatente(),
                0,
                procesadorPago.getMetodoPago().name(),
                vehiculo.obtenerFaseActual());
    }

    public FinalizarAlquilerResponse finalizar(FinalizarAlquilerRequest request) {
        validarFinalizarRequest(request);
        Vehiculo vehiculo = buscarVehiculo(request.getPatente());

        double costoFinal = estrategiaTarifaActiva.calcular(request.getMinutosTranscurridos(), vehiculo);
        vehiculo.finalizarViaje();

        return new FinalizarAlquilerResponse(
                "Viaje finalizado correctamente.",
                vehiculo.getPatente(),
                costoFinal,
                request.getMinutosTranscurridos(),
                vehiculo.obtenerFaseActual(),
                estrategiaTarifaActiva.obtenerNombre());
    }

    public CambioTarifaResponse cambiarCriterioTarifa(CambioTarifaRequest request) {
        if (request == null || request.getCriterio() == null || request.getCriterio().trim().isEmpty()) {
            throw new SolicitudInvalidaException("Debe indicar el criterio de tarifa.");
        }

        this.estrategiaTarifaActiva = estrategiaTarifaFactory.obtenerEstrategia(request.getCriterio());
        return new CambioTarifaResponse(
                "Criterio de tarifa actualizado correctamente.",
                estrategiaTarifaActiva.obtenerNombre());
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

    private void validarDesbloqueoRequest(DesbloqueoRequest request) {
        if (request == null) {
            throw new SolicitudInvalidaException("La solicitud de desbloqueo es obligatoria.");
        }
        if (request.getIdUsuario() == null) {
            throw new SolicitudInvalidaException("El idUsuario es obligatorio.");
        }
        if (request.getPatente() == null || request.getPatente().trim().isEmpty()) {
            throw new SolicitudInvalidaException("La patente es obligatoria.");
        }
        if (request.getMetodoPago() == null || request.getMetodoPago().trim().isEmpty()) {
            throw new SolicitudInvalidaException("El metodo de pago es obligatorio.");
        }
    }

    private void validarFinalizarRequest(FinalizarAlquilerRequest request) {
        if (request == null) {
            throw new SolicitudInvalidaException("La solicitud de finalizacion es obligatoria.");
        }
        if (request.getPatente() == null || request.getPatente().trim().isEmpty()) {
            throw new SolicitudInvalidaException("La patente es obligatoria.");
        }
        if (request.getMinutosTranscurridos() <= 0) {
            throw new SolicitudInvalidaException("Los minutos transcurridos deben ser mayores a cero.");
        }
    }

    private EstacionAnclaje crearEstacionInicial() {
        EstacionAnclaje estacionInicial = new EstacionAnclaje("Estacion Centro");
        estacionInicial.agregarVehiculo(new Monopatin("MONO-001", 80, 1200, true));
        estacionInicial.agregarVehiculo(new BicicletaElectrica("BICI-001", 60, 1000, 2500));
        estacionInicial.agregarVehiculo(new Monopatin("MONO-002", 10, 900, false));
        return estacionInicial;
    }

    private Map<Long, Usuario> crearUsuariosIniciales() {
        Map<Long, Usuario> usuariosIniciales = new HashMap<>();
        usuariosIniciales.put(1L, new UsuarioRegular(1L, "Usuario Regular"));
        usuariosIniciales.put(2L, new UsuarioPremium(2L, "Usuario Premium", 15));
        return usuariosIniciales;
    }
}
