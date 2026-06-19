package com.hexa.service.tarifa;

import org.springframework.stereotype.Component;

import com.hexa.models.Vehiculo;

@Component
public class TarifaTemporalClimatico implements EstrategiaTarifa {

    private static final double RECARGO_SEGURIDAD = 150;

    @Override
    public double calcular(double minutosTranscurridos, Vehiculo vehiculo) {
        double costoBase = minutosTranscurridos * vehiculo.getTarifaBase();
        return costoBase + RECARGO_SEGURIDAD;
    }

    @Override
    public String obtenerCodigo() {
        return "TEMPORAL_CLIMATICO";
    }

    @Override
    public String obtenerNombre() {
        return "Criterio por Temporal Climatico";
    }
}
