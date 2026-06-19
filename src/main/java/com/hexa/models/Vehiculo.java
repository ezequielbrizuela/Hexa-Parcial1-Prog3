package com.hexa.models;

import com.hexa.models.estado.EstadoEnEspera;
import com.hexa.models.estado.EstadoVehiculo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Vehiculo implements Comparable<Vehiculo> {
    private String patente;
    private int bateria;
    private double tarifaBase;
    private EstadoVehiculo estado = new EstadoEnEspera();

    public Vehiculo(String patente, int bateria, double tarifaBase) {
        this.patente = patente;
        this.bateria = bateria;
        this.tarifaBase = tarifaBase;
        this.estado = new EstadoEnEspera();
    }

    public void iniciarViaje() {
        estado.iniciarViaje(this);
    }

    public void finalizarViaje() {
        estado.finalizarViaje(this);
    }

    public void enviarAReparacion() {
        estado.enviarAReparacion(this);
    }

    public void marcarDisponible() {
        estado.marcarDisponible(this);
    }

    public String obtenerFaseActual() {
        return estado.obtenerNombreAmigable();
    }

    @Override
    public int compareTo(Vehiculo otroVehiculo) {
        int comparacionBateria = Integer.compare(this.bateria, otroVehiculo.bateria);
        if (comparacionBateria != 0) {
            return comparacionBateria;
        }
        return this.patente.compareToIgnoreCase(otroVehiculo.patente);
    }
}
