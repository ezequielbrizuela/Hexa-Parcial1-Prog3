package com.hexa.service;

import com.hexa.models.MetodoPago;

public interface ProcesadorPago {

    void cobrar(double monto);
    MetodoPago getMetodoPago();
}
