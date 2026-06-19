package com.hexa.comparators;

import java.util.Comparator;

import com.hexa.models.Vehiculo;

public class ComparadorTarifaBaseDescendente implements Comparator<Vehiculo> {

    @Override
    public int compare(Vehiculo primerVehiculo, Vehiculo segundoVehiculo) {
        int comparacionTarifa = Double.compare(segundoVehiculo.getTarifaBase(), primerVehiculo.getTarifaBase());
        if (comparacionTarifa != 0) {
            return comparacionTarifa;
        }
        return primerVehiculo.getPatente().compareToIgnoreCase(segundoVehiculo.getPatente());
    }
}
