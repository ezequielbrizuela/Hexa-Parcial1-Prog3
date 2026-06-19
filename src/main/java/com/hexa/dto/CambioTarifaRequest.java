package com.hexa.dto;

public class CambioTarifaRequest {

    private String criterio;

    public CambioTarifaRequest() {
    }

    public CambioTarifaRequest(String criterio) {
        this.criterio = criterio;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }
}
