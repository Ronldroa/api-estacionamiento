package com.parking.estacionamiento.controller;

import com.parking.estacionamiento.dto.EstacionamientoDto;
import com.parking.estacionamiento.service.EstacionamientoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class EstacionamientoController {

    @Autowired
    EstacionamientoService service;

    @PostMapping("/registrar-entrada")
    public ResponseEntity<String> crearEntada(@Valid @RequestBody EstacionamientoDto request) {
        service.registrarEntrada(request);
       return ResponseEntity.ok().build();
       // return new ResponseEntity<String>("gut gemacht", HttpStatus.OK);
    }

    @PostMapping("/registrar-salida")
    public ResponseEntity<String> crearSalida(@Valid @RequestBody EstacionamientoDto request) {

        return ResponseEntity.status(HttpStatus.OK).body(service.registrarSalida(request));
    }

}



