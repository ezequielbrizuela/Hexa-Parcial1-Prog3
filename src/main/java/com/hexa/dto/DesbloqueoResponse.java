package com.hexa.dto;

public class DesbloqueoResponse {

    private String mensaje;
    private String vehiculoDesbloqueado;
    private double montoCobrado;
    private String metodoPago;

    public DesbloqueoResponse() {
    }   

    public DesbloqueoResponse(String mensaje, String vehiculoDesbloqueado, double montoCobrado, String metodoPago) {
        this.mensaje = mensaje;
        this.vehiculoDesbloqueado = vehiculoDesbloqueado;
        this.montoCobrado = montoCobrado;
        this.metodoPago = metodoPago;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getVehiculoDesbloqueado() {
        return vehiculoDesbloqueado;
    }

    public void setVehiculoDesbloqueado(String vehiculoDesbloqueado) {
        this.vehiculoDesbloqueado = vehiculoDesbloqueado;
    }

    public double getMontoCobrado() {
        return montoCobrado;
    }

    public void setMontoCobrado(double montoCobrado) {
        this.montoCobrado = montoCobrado;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}
