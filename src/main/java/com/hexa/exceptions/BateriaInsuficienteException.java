package com.hexa.exceptions;

public class BateriaInsuficienteException extends RuntimeException {

    public BateriaInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
