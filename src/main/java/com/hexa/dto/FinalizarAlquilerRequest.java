package com.hexa.dto;

public class FinalizarAlquilerRequest {

    private String patente;
    private double minutosTranscurridos;

    public FinalizarAlquilerRequest() {
    }

    public FinalizarAlquilerRequest(String patente, double minutosTranscurridos) {
        this.patente = patente;
        this.minutosTranscurridos = minutosTranscurridos;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public double getMinutosTranscurridos() {
        return minutosTranscurridos;
    }

    public void setMinutosTranscurridos(double minutosTranscurridos) {
        this.minutosTranscurridos = minutosTranscurridos;
    }
}
