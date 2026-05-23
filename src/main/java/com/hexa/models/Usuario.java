package com.hexa.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Usuario {
    private final Long id;
    private String nombreCompleto;

    public Usuario(Long id, String nombreCompleto) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
    }

    public double aplicarDescuento(double importe) {
        return importe;
    }

    public double calcularTotalAPagar(Vehiculo vehiculo) {
        return aplicarDescuento(vehiculo.getTarifaBase());
    }
}
