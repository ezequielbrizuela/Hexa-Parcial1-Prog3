package com.hexa.dto;

public class ErrorResponse {

    private String error;
    private String detalle;

    public ErrorResponse() {
    }

    public ErrorResponse(String error, String detalle) {
        this.error = error;
        this.detalle = detalle;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}
