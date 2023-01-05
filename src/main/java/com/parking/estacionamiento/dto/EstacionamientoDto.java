package com.parking.estacionamiento.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parking.estacionamiento.modelo.TipoAutomovil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EstacionamientoDto {

    @JsonProperty("tipo_automovil")
    private TipoAutomovil tipoAutomovil;

    @NotNull
    private String placa;

}
