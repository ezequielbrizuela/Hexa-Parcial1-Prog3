package com.hexa.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Monopatin extends Vehiculo {
    private boolean amortiguacionReforzada;

    public Monopatin(String patente, int bateria, double tarifaBase, boolean amortiguacionReforzada) {
        super(patente, bateria, tarifaBase);
        this.amortiguacionReforzada = amortiguacionReforzada;
    }
}
