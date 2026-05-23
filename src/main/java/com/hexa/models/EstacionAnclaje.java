package com.hexa.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EstacionAnclaje {

    private String nombre;
    private List<Vehiculo> vehiculosDisponibles;

    public EstacionAnclaje() {
        this.vehiculosDisponibles = new ArrayList<>();
    }

    public EstacionAnclaje(String nombre) {
        this.nombre = nombre;
        this.vehiculosDisponibles = new ArrayList<>();
    }

    public EstacionAnclaje(String nombre, List<Vehiculo> vehiculosDisponibles) {
        this.nombre = nombre;
        this.vehiculosDisponibles = vehiculosDisponibles;
    }

    public Vehiculo buscarVehiculoPorPatente(String patente) {
        for (Vehiculo vehiculo : vehiculosDisponibles) {
            if (vehiculo.getPatente().equalsIgnoreCase(patente)) {
                return vehiculo;
            }
        }
        return null;
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        vehiculosDisponibles.add(vehiculo);
    }

    public void quitarVehiculo(Vehiculo vehiculo) {
        vehiculosDisponibles.remove(vehiculo);
    }
}
