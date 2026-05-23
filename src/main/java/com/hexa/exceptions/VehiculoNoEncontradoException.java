package com.hexa.exceptions;

public class VehiculoNoEncontradoException extends RuntimeException {

    public VehiculoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
