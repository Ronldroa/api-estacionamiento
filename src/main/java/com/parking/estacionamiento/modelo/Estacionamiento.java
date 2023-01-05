package com.parking.estacionamiento.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "sistema")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estacionamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "id_tipo_vehiculo")
    private int idTipoVehiculo;

    @ManyToOne( fetch = FetchType.LAZY, optional = true)

    @JoinColumn(name = "id_tipo_vehiculo", insertable = false, updatable = false, nullable = true)
    private TipoVehiculo tipoVehiculo;

    @Column(name = "fecha_hora_entrada")
    private Date fechaHoraEntrada;

    @Column(name = "fecha_hora_salida")
    private Date fechaHoraSalida;

    @NotNull
    @Column(name = "placa", length = 10)
    private String placa;


}
