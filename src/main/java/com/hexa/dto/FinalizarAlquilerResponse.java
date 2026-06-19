package com.hexa.dto;

public class FinalizarAlquilerResponse {

    private String mensaje;
    private String patente;
    private double costoFinal;
    private double minutosTranscurridos;
    private String faseActual;
    private String criterioTarifa;

    public FinalizarAlquilerResponse() {
    }

    public FinalizarAlquilerResponse(String mensaje, String patente, double costoFinal, double minutosTranscurridos,
            String faseActual, String criterioTarifa) {
        this.mensaje = mensaje;
        this.patente = patente;
        this.costoFinal = costoFinal;
        this.minutosTranscurridos = minutosTranscurridos;
        this.faseActual = faseActual;
        this.criterioTarifa = criterioTarifa;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public double getCostoFinal() {
        return costoFinal;
    }

    public void setCostoFinal(double costoFinal) {
        this.costoFinal = costoFinal;
    }

    public double getMinutosTranscurridos() {
        return minutosTranscurridos;
    }

    public void setMinutosTranscurridos(double minutosTranscurridos) {
        this.minutosTranscurridos = minutosTranscurridos;
    }

    public String getFaseActual() {
        return faseActual;
    }

    public void setFaseActual(String faseActual) {
        this.faseActual = faseActual;
    }

    public String getCriterioTarifa() {
        return criterioTarifa;
    }

    public void setCriterioTarifa(String criterioTarifa) {
        this.criterioTarifa = criterioTarifa;
    }
}
