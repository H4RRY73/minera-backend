package com.platform.ventistorebackend.mining.application.internal.queryservices;

import com.platform.ventistorebackend.mining.domain.model.aggregates.ProductionRecord;
import com.platform.ventistorebackend.mining.infrastructure.persistence.jpa.repositories.ProductionRecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiningQueryServiceImpl {

    private final ProductionRecordRepository productionRepository;

    public MiningQueryServiceImpl(ProductionRecordRepository productionRepository) {
        this.productionRepository = productionRepository;
    }

    /**
     * Obtener todos los registros con paginación
     */
    public Page<ProductionRecord> getAllProduction(Pageable pageable) {
        return productionRepository.findAll(pageable);
    }

    /**
     * Obtener registros por ID de equipo
     */
    public List<ProductionRecord> getProductionByEquipment(String equipmentId) {
        return productionRepository.findByEquipment_EquipmentId(equipmentId);
    }

    /**
     * Obtener estadísticas crudas por zona (retorna Object[])
     */
    public List<Object[]> getStatsByZone(String zoneName) {
        return productionRepository.getStatsByZone(zoneName);
    }

    /**
     * Obtener ranking de operadores crudo (retorna Object[])
     */
    public List<Object[]> getTopOperators() {
        return productionRepository.getTopOperators();
    }
}
