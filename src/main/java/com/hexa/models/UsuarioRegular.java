package com.hexa.models;

import lombok.ToString;

@ToString(callSuper = true)
public class UsuarioRegular extends Usuario {
    public UsuarioRegular(Long id, String nombreCompleto) {
        super(id, nombreCompleto);
    }

    @Override
    public double aplicarDescuento(double importe) {
        return importe;
    }
}
