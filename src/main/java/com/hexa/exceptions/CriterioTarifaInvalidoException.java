package com.hexa.exceptions;

public class CriterioTarifaInvalidoException extends RuntimeException {

    public CriterioTarifaInvalidoException(String mensaje) {
        super(mensaje);
    }
}
