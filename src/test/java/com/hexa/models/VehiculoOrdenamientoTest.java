package com.hexa.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.hexa.comparators.ComparadorTarifaBaseDescendente;

class VehiculoOrdenamientoTest {

    @Test
    void ordenNaturalPriorizaBateriaDeMenorAMayor() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(new Monopatin("MONO-001", 80, 1200, true));
        vehiculos.add(new BicicletaElectrica("BICI-001", 15, 1000, 2500));
        vehiculos.add(new Monopatin("MONO-002", 40, 900, false));

        Collections.sort(vehiculos);

        assertEquals("BICI-001", vehiculos.get(0).getPatente());
        assertEquals("MONO-002", vehiculos.get(1).getPatente());
        assertEquals("MONO-001", vehiculos.get(2).getPatente());
    }

    @Test
    void ordenNaturalUsaPatenteComoDesempate() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(new Monopatin("MONO-002", 40, 900, false));
        vehiculos.add(new BicicletaElectrica("BICI-001", 40, 1000, 2500));

        Collections.sort(vehiculos);

        assertEquals("BICI-001", vehiculos.get(0).getPatente());
        assertEquals("MONO-002", vehiculos.get(1).getPatente());
    }

    @Test
    void comparadorExternoOrdenaTarifaBaseDeMayorAMenor() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(new Monopatin("MONO-001", 80, 1200, true));
        vehiculos.add(new BicicletaElectrica("BICI-001", 60, 1000, 2500));
        vehiculos.add(new Monopatin("MONO-002", 10, 1500, false));

        Collections.sort(vehiculos, new ComparadorTarifaBaseDescendente());

        assertEquals("MONO-002", vehiculos.get(0).getPatente());
        assertEquals("MONO-001", vehiculos.get(1).getPatente());
        assertEquals("BICI-001", vehiculos.get(2).getPatente());
    }
}
