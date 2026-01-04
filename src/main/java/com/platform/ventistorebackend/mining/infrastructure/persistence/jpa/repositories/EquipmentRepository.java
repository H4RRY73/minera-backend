package com.platform.ventistorebackend.mining.infrastructure.persistence.jpa.repositories;

import com.platform.ventistorebackend.mining.domain.model.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, String> {
}
