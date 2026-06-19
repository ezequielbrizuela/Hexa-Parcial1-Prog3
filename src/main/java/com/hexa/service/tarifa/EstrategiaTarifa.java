package com.hexa.service.tarifa;

import com.hexa.models.Vehiculo;

public interface EstrategiaTarifa {

    double calcular(double minutosTranscurridos, Vehiculo vehiculo);

    String obtenerCodigo();

    String obtenerNombre();
}
