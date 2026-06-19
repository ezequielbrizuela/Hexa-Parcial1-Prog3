package com.hexa.controllers;

import com.hexa.dto.CambioTarifaRequest;
import com.hexa.dto.CambioTarifaResponse;
import com.hexa.dto.DesbloqueoRequest;
import com.hexa.dto.DesbloqueoResponse;
import com.hexa.dto.ErrorResponse;
import com.hexa.dto.FinalizarAlquilerRequest;
import com.hexa.dto.FinalizarAlquilerResponse;
import com.hexa.exceptions.BateriaInsuficienteException;
import com.hexa.exceptions.CriterioTarifaInvalidoException;
import com.hexa.exceptions.MetodoPagoInvalidoException;
import com.hexa.exceptions.SolicitudInvalidaException;
import com.hexa.exceptions.TransicionEstadoInvalidaException;
import com.hexa.exceptions.UsuarioNoEncontradoException;
import com.hexa.exceptions.VehiculoDuplicadoException;
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

    @PostMapping("/finalizar")
    public ResponseEntity<FinalizarAlquilerResponse> finalizar(@RequestBody FinalizarAlquilerRequest request) {
        FinalizarAlquilerResponse response = ecoRideService.finalizar(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/criterio-tarifa")
    public ResponseEntity<CambioTarifaResponse> cambiarCriterioTarifa(@RequestBody CambioTarifaRequest request) {
        CambioTarifaResponse response = ecoRideService.cambiarCriterioTarifa(request);
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

    @ExceptionHandler(TransicionEstadoInvalidaException.class)
    public ResponseEntity<ErrorResponse> manejarTransicionEstadoInvalida(TransicionEstadoInvalidaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("TRANSICION_ESTADO_INVALIDA", ex.getMessage()));
    }

    @ExceptionHandler(CriterioTarifaInvalidoException.class)
    public ResponseEntity<ErrorResponse> manejarCriterioTarifaInvalido(CriterioTarifaInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("CRITERIO_TARIFA_INVALIDO", ex.getMessage()));
    }

    @ExceptionHandler(SolicitudInvalidaException.class)
    public ResponseEntity<ErrorResponse> manejarSolicitudInvalida(SolicitudInvalidaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("SOLICITUD_INVALIDA", ex.getMessage()));
    }

    @ExceptionHandler(VehiculoDuplicadoException.class)
    public ResponseEntity<ErrorResponse> manejarVehiculoDuplicado(VehiculoDuplicadoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("VEHICULO_DUPLICADO", ex.getMessage()));
    }
}
