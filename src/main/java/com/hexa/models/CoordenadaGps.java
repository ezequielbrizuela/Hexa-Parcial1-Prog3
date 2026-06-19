package com.hexa.models;

public class CoordenadaGps {

    private double latitud;
    private double longitud;

    public CoordenadaGps() {
    }

    public CoordenadaGps(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public boolean esValida() {
        return latitud >= -90 && latitud <= 90 && longitud >= -180 && longitud <= 180;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        CoordenadaGps otraCoordenada = (CoordenadaGps) obj;
        return Double.compare(latitud, otraCoordenada.latitud) == 0
                && Double.compare(longitud, otraCoordenada.longitud) == 0;
    }

    @Override
    public int hashCode() {
        int resultado = 17;
        long latitudBits = Double.doubleToLongBits(latitud);
        long longitudBits = Double.doubleToLongBits(longitud);
        resultado = 31 * resultado + (int) (latitudBits ^ (latitudBits >>> 32));
        resultado = 31 * resultado + (int) (longitudBits ^ (longitudBits >>> 32));
        return resultado;
    }
}
