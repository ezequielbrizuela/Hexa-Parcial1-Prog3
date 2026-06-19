package com.hexa.models.estado;

import com.hexa.exceptions.TransicionEstadoInvalidaException;
import com.hexa.models.Vehiculo;

public class EstadoEnReparacion implements EstadoVehiculo {

    @Override
    public void iniciarViaje(Vehiculo vehiculo) {
        throw new TransicionEstadoInvalidaException("No se puede iniciar el viaje porque el vehiculo esta en reparacion.");
    }

    @Override
    public void finalizarViaje(Vehiculo vehiculo) {
        throw new TransicionEstadoInvalidaException("No se puede finalizar un viaje porque el vehiculo esta en reparacion.");
    }

    @Override
    public void enviarAReparacion(Vehiculo vehiculo) {
        throw new TransicionEstadoInvalidaException("El vehiculo ya se encuentra en reparacion.");
    }

    @Override
    public void marcarDisponible(Vehiculo vehiculo) {
        vehiculo.setEstado(new EstadoEnEspera());
    }

    @Override
    public String obtenerNombreAmigable() {
        return "En Reparacion";
    }
}
