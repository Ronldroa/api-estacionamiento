package com.parking.estacionamiento.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipo_vehiculo")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TipoVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_automovil", length = 10)
    private TipoAutomovil tipoAutomovil;

    @OneToMany(mappedBy = "tipoVehiculo")
    private List<Estacionamiento> entradas;

}
