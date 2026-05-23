package com.hexa.controllers;

import com.hexa.dto.DesbloqueoRequest;
import com.hexa.dto.DesbloqueoResponse;
import com.hexa.dto.ErrorResponse;
import com.hexa.exceptions.BateriaInsuficienteException;
import com.hexa.exceptions.MetodoPagoInvalidoException;
import com.hexa.exceptions.UsuarioNoEncontradoException;
import com.hexa.exceptions.VehiculoNoEncontradoException;
import com.hexa.service.EcoRideService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alquileres")
public class AlquilerController {

    private final EcoRideService ecoRideService;

    public AlquilerController(EcoRideService ecoRideService) {
        this.ecoRideService = ecoRideService;
    }

    @PostMapping("/desbloquear")
    public ResponseEntity<DesbloqueoResponse> desbloquear(@RequestBody DesbloqueoRequest request) {
        DesbloqueoResponse response = ecoRideService.desbloquear(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/desbloquear")
    public ResponseEntity<DesbloqueoResponse> desbloquearPorParametros(
            @RequestParam Long idUsuario,
            @RequestParam String patente,
            @RequestParam String metodoPago) {
        DesbloqueoRequest request = new DesbloqueoRequest(idUsuario, patente, metodoPago);
        DesbloqueoResponse response = ecoRideService.desbloquear(request);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(VehiculoNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> manejarVehiculoNoEncontrado(VehiculoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("VEHICULO_NO_ENCONTRADO", ex.getMessage()));
    }

    @ExceptionHandler(BateriaInsuficienteException.class)
    public ResponseEntity<ErrorResponse> manejarBateriaInsuficiente(BateriaInsuficienteException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("BATERIA_INSUFICIENTE", ex.getMessage()));
    }

    @ExceptionHandler(MetodoPagoInvalidoException.class)
    public ResponseEntity<ErrorResponse> manejarMetodoPagoInvalido(MetodoPagoInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("METODO_PAGO_INVALIDO", ex.getMessage()));
    }

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<ErrorResponse> manejarUsuarioNoEncontrado(UsuarioNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("USUARIO_NO_ENCONTRADO", ex.getMessage()));
    }
}
