package com.hexa.service.tarifa;

import org.springframework.stereotype.Component;

import com.hexa.models.Vehiculo;

@Component
public class TarifaEstandar implements EstrategiaTarifa {

    @Override
    public double calcular(double minutosTranscurridos, Vehiculo vehiculo) {
        return minutosTranscurridos * vehiculo.getTarifaBase();
    }

    @Override
    public String obtenerCodigo() {
        return "ESTANDAR";
    }

    @Override
    public String obtenerNombre() {
        return "Criterio Estandar";
    }
}
