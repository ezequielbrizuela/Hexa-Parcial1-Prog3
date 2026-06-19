package com.hexa.models.estado;

import com.hexa.exceptions.TransicionEstadoInvalidaException;
import com.hexa.models.Vehiculo;

public class EstadoEnEspera implements EstadoVehiculo {

    @Override
    public void iniciarViaje(Vehiculo vehiculo) {
        vehiculo.setEstado(new EstadoEnViaje());
    }

    @Override
    public void finalizarViaje(Vehiculo vehiculo) {
        throw new TransicionEstadoInvalidaException("No se puede finalizar un viaje porque el vehiculo esta en espera.");
    }

    @Override
    public void enviarAReparacion(Vehiculo vehiculo) {
        vehiculo.setEstado(new EstadoEnReparacion());
    }

    @Override
    public void marcarDisponible(Vehiculo vehiculo) {
        throw new TransicionEstadoInvalidaException("El vehiculo ya se encuentra en espera.");
    }

    @Override
    public String obtenerNombreAmigable() {
        return "En Espera";
    }
}
