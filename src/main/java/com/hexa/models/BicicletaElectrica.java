package com.hexa.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class BicicletaElectrica extends Vehiculo {

    private int capacidadCanastoCm3;

    public BicicletaElectrica(String patente, int bateria, double tarifaBase, int capacidadCanastoCm3) {
        super(patente, bateria, tarifaBase);
        this.capacidadCanastoCm3 = capacidadCanastoCm3;
    }
}
