package com.parking.sistema.repository;

import com.parking.sistema.model.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SistemaRepository extends JpaRepository<Sistema,Integer> {
}
