package com.hexa.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hexa.exceptions.SolicitudInvalidaException;
import com.hexa.models.CoordenadaGps;

@Service
public class GpsService {

    public List<CoordenadaGps> deduplicarAlertasGps(List<CoordenadaGps> reportes) {
        Set<CoordenadaGps> coordenadasUnicas = new LinkedHashSet<>();

        if (reportes == null) {
            return new ArrayList<>();
        }

        for (CoordenadaGps reporte : reportes) {
            if (reporte == null) {
                continue;
            }
            validarCoordenada(reporte);
            coordenadasUnicas.add(reporte);
        }

        return new ArrayList<>(coordenadasUnicas);
    }

    private void validarCoordenada(CoordenadaGps coordenada) {
        if (!coordenada.esValida()) {
            throw new SolicitudInvalidaException("La coordenada GPS está |fuera de rango.");
        }
    }
}
