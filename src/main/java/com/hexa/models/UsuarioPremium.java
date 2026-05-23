package com.hexa.models;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class UsuarioPremium extends Usuario {
    private final double porcentajeDescuento;

    public UsuarioPremium(Long id, String nombreCompleto, double porcentajeDescuento) {
        super(id, nombreCompleto);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public double aplicarDescuento(double importe) {
        return importe - (importe * porcentajeDescuento / 100);
    }

    @Override
    public double calcularTotalAPagar(Vehiculo vehiculo) {
        return aplicarDescuento(vehiculo.getTarifaBase());
    }
}
