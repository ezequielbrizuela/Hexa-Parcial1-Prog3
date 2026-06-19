package com.hexa.service.tarifa;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hexa.exceptions.CriterioTarifaInvalidoException;

@Component
public class EstrategiaTarifaFactory {

    private final List<EstrategiaTarifa> estrategias;

    public EstrategiaTarifaFactory(List<EstrategiaTarifa> estrategias) {
        this.estrategias = estrategias;
    }

    public EstrategiaTarifa obtenerEstrategia(String criterio) {
        if (criterio == null) {
            throw new CriterioTarifaInvalidoException("El criterio de tarifa es obligatorio.");
        }

        String criterioNormalizado = criterio.trim().toUpperCase();
        for (EstrategiaTarifa estrategia : estrategias) {
            if (estrategia.obtenerCodigo().equals(criterioNormalizado)) {
                return estrategia;
            }
        }

        throw new CriterioTarifaInvalidoException("Criterio de tarifa invalido. Use ESTANDAR, HORA_PICO o TEMPORAL_CLIMATICO.");
    }
}
