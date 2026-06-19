package com.hexa.dto;

public class VehiculoResponse {

    private String patente;
    private int bateria;
    private double tarifaBase;
    private String faseActual;
    private String tipo;

    public VehiculoResponse() {
    }

    public VehiculoResponse(String patente, int bateria, double tarifaBase, String faseActual, String tipo) {
        this.patente = patente;
        this.bateria = bateria;
        this.tarifaBase = tarifaBase;
        this.faseActual = faseActual;
        this.tipo = tipo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public String getFaseActual() {
        return faseActual;
    }

    public void setFaseActual(String faseActual) {
        this.faseActual = faseActual;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
