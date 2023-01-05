package com.parking.sistema.dto;

import com.parking.vehiculos.model.Estacionamiento;
import com.parking.sistema.model.Sistema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Collection;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SistemaDto {
    private Integer id;

    private Date fecha_hoy;

    private Date hora_entrada;

    private Date hora_salida;

    private int numero_puesto;

    private String placa;

    private int id_tipo;

    @OneToOne(mappedBy = "sistemaDto")
    private Estacionamiento employee;
}
