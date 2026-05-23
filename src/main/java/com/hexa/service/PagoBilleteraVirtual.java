package com.hexa.service;

import org.springframework.stereotype.Component;

import com.hexa.models.MetodoPago;

@Component
public class PagoBilleteraVirtual implements ProcesadorPago {

    @Override
    public void cobrar(double monto) {
        // Simulacion de cobro con billetera virtual.
    }

    @Override
    public MetodoPago getMetodoPago() {
        return MetodoPago.BILLETERA;
    }
}
