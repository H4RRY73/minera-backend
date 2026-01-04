package com.platform.ventistorebackend.mining.infrastructure.persistence.jpa.repositories;

import com.platform.ventistorebackend.mining.domain.model.entities.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {
    Optional<Operator> findByFullName(String fullName);
}
