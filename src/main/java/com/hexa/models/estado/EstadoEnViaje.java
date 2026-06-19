package com.hexa.models.estado;

import com.hexa.exceptions.TransicionEstadoInvalidaException;
import com.hexa.models.Vehiculo;

public class EstadoEnViaje implements EstadoVehiculo {

    @Override
    public void iniciarViaje(Vehiculo vehiculo) {
        throw new TransicionEstadoInvalidaException("No se puede iniciar el viaje porque el vehiculo ya esta en viaje.");
    }

    @Override
    public void finalizarViaje(Vehiculo vehiculo) {
        vehiculo.setEstado(new EstadoEnEspera());
    }

    @Override
    public void enviarAReparacion(Vehiculo vehiculo) {
        throw new TransicionEstadoInvalidaException("No se puede enviar a reparacion un vehiculo que esta en viaje.");
    }

    @Override
    public void marcarDisponible(Vehiculo vehiculo) {
        throw new TransicionEstadoInvalidaException("No se puede marcar disponible un vehiculo que aun esta en viaje.");
    }

    @Override
    public String obtenerNombreAmigable() {
        return "En Viaje";
    }
}
