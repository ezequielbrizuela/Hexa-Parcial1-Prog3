package com.hexa.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexa.dto.VehiculoResponse;
import com.hexa.models.Vehiculo;
import com.hexa.service.EcoRideService;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private final EcoRideService ecoRideService;

    public VehiculoController(EcoRideService ecoRideService) {
        this.ecoRideService = ecoRideService;
    }

    @GetMapping("/prioridad-carga")
    public ResponseEntity<List<VehiculoResponse>> obtenerVehiculosPorPrioridadCarga() {
        List<Vehiculo> vehiculos = ecoRideService.obtenerVehiculosPorPrioridadCarga();
        return ResponseEntity.ok(convertirAResponses(vehiculos));
    }

    @GetMapping("/tarifa-descendente")
    public ResponseEntity<List<VehiculoResponse>> obtenerVehiculosPorTarifaDescendente() {
        List<Vehiculo> vehiculos = ecoRideService.obtenerVehiculosPorTarifaDescendente();
        return ResponseEntity.ok(convertirAResponses(vehiculos));
    }

    private List<VehiculoResponse> convertirAResponses(List<Vehiculo> vehiculos) {
        List<VehiculoResponse> responses = new ArrayList<>();

        for (Vehiculo vehiculo : vehiculos) {
            responses.add(convertirAResponse(vehiculo));
        }

        return responses;
    }

    private VehiculoResponse convertirAResponse(Vehiculo vehiculo) {
        return new VehiculoResponse(
                vehiculo.getPatente(),
                vehiculo.getBateria(),
                vehiculo.getTarifaBase(),
                vehiculo.obtenerFaseActual(),
                vehiculo.getClass().getSimpleName());
    }
}
