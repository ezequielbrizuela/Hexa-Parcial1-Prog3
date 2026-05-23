package com.hexa.dto;

public class DesbloqueoRequest {

    private Long idUsuario;
    private String patente;
    private String metodoPago;

    public DesbloqueoRequest() {
    }

    public DesbloqueoRequest(Long idUsuario, String patente, String metodoPago) {
        this.idUsuario = idUsuario;
        this.patente = patente;
        this.metodoPago = metodoPago;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}
