package com.hexa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.hexa.exceptions.SolicitudInvalidaException;
import com.hexa.models.CoordenadaGps;

class GpsServiceTest {

    private final GpsService gpsService = new GpsService();

    @Test
    void deduplicarAlertasGpsEliminaDuplicadosYConservaOrden() {
        List<CoordenadaGps> reportes = new ArrayList<>();
        reportes.add(new CoordenadaGps(-29.4131, -66.8558));
        reportes.add(new CoordenadaGps(-29.4100, -66.8500));
        reportes.add(new CoordenadaGps(-29.4131, -66.8558));
        reportes.add(new CoordenadaGps(-29.4200, -66.8600));

        List<CoordenadaGps> resultado = gpsService.deduplicarAlertasGps(reportes);

        assertEquals(3, resultado.size());
        assertEquals(new CoordenadaGps(-29.4131, -66.8558), resultado.get(0));
        assertEquals(new CoordenadaGps(-29.4100, -66.8500), resultado.get(1));
        assertEquals(new CoordenadaGps(-29.4200, -66.8600), resultado.get(2));
    }

    @Test
    void deduplicarAlertasGpsIgnoraReportesNulos() {
        List<CoordenadaGps> reportes = new ArrayList<>();
        reportes.add(null);
        reportes.add(new CoordenadaGps(-29.4131, -66.8558));
        reportes.add(null);

        List<CoordenadaGps> resultado = gpsService.deduplicarAlertasGps(reportes);

        assertEquals(1, resultado.size());
        assertEquals(new CoordenadaGps(-29.4131, -66.8558), resultado.get(0));
    }

    @Test
    void deduplicarAlertasGpsRechazaCoordenadasFueraDeRango() {
        List<CoordenadaGps> reportes = new ArrayList<>();
        reportes.add(new CoordenadaGps(-91, -66.8558));

        assertThrows(SolicitudInvalidaException.class, new Executable() {
            @Override
            public void execute() {
                gpsService.deduplicarAlertasGps(reportes);
            }
        });
    }

    @Test
    void deduplicarAlertasGpsDevuelveListaVaciaSiLaEntradaEsNula() {
        List<CoordenadaGps> resultado = gpsService.deduplicarAlertasGps(null);

        assertEquals(0, resultado.size());
    }
}
