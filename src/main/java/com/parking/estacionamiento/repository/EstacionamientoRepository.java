package com.parking.estacionamiento.repository;

import com.parking.estacionamiento.modelo.Estacionamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EstacionamientoRepository extends JpaRepository<Estacionamiento, Integer> {

    Optional<Estacionamiento> findByPlaca(String placa);

    //findByPlacaAndFechaHoraSalidaEndIsNull

    @Query("select e from Estacionamiento e where e.placa = ?1 and e.fechaHoraSalida = null")
    Optional<Estacionamiento> findByPlacaAndFechaHoraSalidaIsNull(String placa);
}
