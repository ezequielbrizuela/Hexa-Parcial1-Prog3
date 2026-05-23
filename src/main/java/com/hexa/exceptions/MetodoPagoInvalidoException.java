package com.hexa.exceptions;

public class MetodoPagoInvalidoException extends RuntimeException {

    public MetodoPagoInvalidoException(String mensaje) {
        super(mensaje);
    }

    public MetodoPagoInvalidoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
