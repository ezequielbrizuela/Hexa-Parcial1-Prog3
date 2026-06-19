package com.hexa.dto;

public class CambioTarifaResponse {

    private String mensaje;
    private String criterioActivo;

    public CambioTarifaResponse() {
    }

    public CambioTarifaResponse(String mensaje, String criterioActivo) {
        this.mensaje = mensaje;
        this.criterioActivo = criterioActivo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCriterioActivo() {
        return criterioActivo;
    }

    public void setCriterioActivo(String criterioActivo) {
        this.criterioActivo = criterioActivo;
    }
}
