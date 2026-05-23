package com.hexa.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hexa.exceptions.MetodoPagoInvalidoException;
import com.hexa.models.MetodoPago;

@Component
public class ProcesadorPagoFactory {

    private final List<ProcesadorPago> procesadores;

    public ProcesadorPagoFactory(List<ProcesadorPago> procesadores) {
        this.procesadores = procesadores;
    }

    public ProcesadorPago obtenerProcesador(String metodoPagoTexto) {
        try {
            MetodoPago metodoPago = MetodoPago.valueOf(metodoPagoTexto.toUpperCase());

            for (ProcesadorPago procesador : procesadores) {
                if (procesador.getMetodoPago() == metodoPago) {
                    return procesador;
                }
            }

            throw new MetodoPagoInvalidoException("No existe un procesador para el metodo de pago solicitado.", null);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new MetodoPagoInvalidoException("Metodo de pago invalido. Use TARJETA o BILLETERA.", e);
        }
    }
}