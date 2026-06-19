package com.hexa.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hexa.exceptions.SolicitudInvalidaException;
import com.hexa.exceptions.VehiculoDuplicadoException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EstacionAnclaje {

    private String nombre;
    private Map<String, Vehiculo> vehiculosPorPatente;

    public EstacionAnclaje() {
        this.vehiculosPorPatente = new HashMap<>();
    }

    public EstacionAnclaje(String nombre) {
        this.nombre = nombre;
        this.vehiculosPorPatente = new HashMap<>();
    }

    public EstacionAnclaje(String nombre, List<Vehiculo> vehiculosDisponibles) {
        this.nombre = nombre;
        this.vehiculosPorPatente = new HashMap<>();
        for (Vehiculo vehiculo : vehiculosDisponibles) {
            agregarVehiculo(vehiculo);
        }
    }

    public Vehiculo buscarVehiculoPorPatente(String patente) {
        return vehiculosPorPatente.get(normalizarPatente(patente));
    }

    public void agregarVehiculo(Vehiculo vehiculo) {
        validarVehiculo(vehiculo);
        String patenteNormalizada = normalizarPatente(vehiculo.getPatente());

        if (vehiculosPorPatente.containsKey(patenteNormalizada)) {
            throw new VehiculoDuplicadoException("Ya existe un vehiculo registrado con la patente " + vehiculo.getPatente() + ".");
        }

        vehiculosPorPatente.put(patenteNormalizada, vehiculo);
    }

    public void quitarVehiculo(Vehiculo vehiculo) {
        validarVehiculo(vehiculo);
        vehiculosPorPatente.remove(normalizarPatente(vehiculo.getPatente()));
    }

    public List<Vehiculo> obtenerTodosLosVehiculos() {
        return new ArrayList<>(vehiculosPorPatente.values());
    }

    private void validarVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new SolicitudInvalidaException("El vehiculo es obligatorio.");
        }
        normalizarPatente(vehiculo.getPatente());
    }

    private String normalizarPatente(String patente) {
        if (patente == null || patente.trim().isEmpty()) {
            throw new SolicitudInvalidaException("La patente es obligatoria.");
        }
        return patente.trim().toUpperCase();
    }
}
