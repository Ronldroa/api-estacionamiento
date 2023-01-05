package com.parking.sistema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.parking.vehiculos.model.Estacionamiento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sistemas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date fecha_hoy;

    private Date hora_entrada;

    private Date hora_salida;

    private int numero_puesto;

    private String placa;

    private int id_tipo;

}
