package com.hexa.service.tarifa;

import org.springframework.stereotype.Component;

import com.hexa.models.Vehiculo;

@Component
public class TarifaHoraPico implements EstrategiaTarifa {

    private static final double RECARGO_HORA_PICO = 1.40;

    @Override
    public double calcular(double minutosTranscurridos, Vehiculo vehiculo) {
        double costoBase = minutosTranscurridos * vehiculo.getTarifaBase();
        return costoBase * RECARGO_HORA_PICO;
    }

    @Override
    public String obtenerCodigo() {
        return "HORA_PICO";
    }

    @Override
    public String obtenerNombre() {
        return "Criterio de Hora Pico";
    }
}
