package com.hexa.models.estado;

import com.hexa.models.Vehiculo;

public interface EstadoVehiculo {

    void iniciarViaje(Vehiculo vehiculo);

    void finalizarViaje(Vehiculo vehiculo);

    void enviarAReparacion(Vehiculo vehiculo);

    void marcarDisponible(Vehiculo vehiculo);

    String obtenerNombreAmigable();
}
