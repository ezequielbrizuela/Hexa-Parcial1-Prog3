package com.hexa.service;

import org.springframework.stereotype.Component;

import com.hexa.models.MetodoPago;

@Component
public class PagoTarjetaCredito implements ProcesadorPago {

    @Override
    public void cobrar(double monto) {
        // Simulacion de cobro con tarjeta de credito.
    }

    @Override
    public MetodoPago getMetodoPago() {
        return MetodoPago.TARJETA;
    }
}
