package com.platform.ventistorebackend.mining.infrastructure.persistence.jpa.repositories;

import com.platform.ventistorebackend.mining.domain.model.aggregates.ProductionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductionRecordRepository extends JpaRepository<ProductionRecord, Long> {

    List<ProductionRecord> findByEquipment_EquipmentId(String equipmentId);

    // CORRECCIÓN AQUÍ: cambiamos 'p.incidentes' por 'p.incidents'
    @Query("SELECT p.zone as zone, SUM(p.extractedTons) as totalTons, AVG(p.mineralGrade) as avgGrade, SUM(p.incidents) as totalIncidents " +
            "FROM ProductionRecord p WHERE p.zone = :zoneName GROUP BY p.zone")
    List<Object[]> getStatsByZone(String zoneName);

    @Query("SELECT p.operator.fullName as name, SUM(p.extractedTons) as total " +
            "FROM ProductionRecord p GROUP BY p.operator.fullName ORDER BY total DESC")
    List<Object[]> getTopOperators();
}
