package com.sigabem.demo.repository;

import com.sigabem.demo.entity.Frete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreteRepository extends JpaRepository<Frete, Long> {
}