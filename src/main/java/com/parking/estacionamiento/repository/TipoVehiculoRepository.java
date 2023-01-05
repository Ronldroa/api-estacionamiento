package com.parking.estacionamiento.repository;

import com.parking.estacionamiento.modelo.TipoAutomovil;
import com.parking.estacionamiento.modelo.TipoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TipoVehiculoRepository extends JpaRepository<TipoVehiculo, Integer> {
    @Query("select t from TipoVehiculo t where t.tipoAutomovil = ?1")
    Optional<TipoVehiculo> findByTipoAutomovil(TipoAutomovil tipoAutomovil);
}
