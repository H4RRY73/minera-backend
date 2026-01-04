package com.platform.ventistorebackend.mining.application.internal.commandservices;

import com.platform.ventistorebackend.mining.domain.model.aggregates.ProductionRecord;
import com.platform.ventistorebackend.mining.domain.model.commands.CreateProductionRecordCommand;
import com.platform.ventistorebackend.mining.domain.model.entities.Equipment;
import com.platform.ventistorebackend.mining.domain.model.entities.Operator;
import com.platform.ventistorebackend.mining.infrastructure.persistence.jpa.repositories.EquipmentRepository;
import com.platform.ventistorebackend.mining.infrastructure.persistence.jpa.repositories.OperatorRepository;
import com.platform.ventistorebackend.mining.infrastructure.persistence.jpa.repositories.ProductionRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MiningCommandServiceImpl {
    private final ProductionRecordRepository productionRepository;
    private final EquipmentRepository equipmentRepository;
    private final OperatorRepository operatorRepository;

    public MiningCommandServiceImpl(ProductionRecordRepository productionRepository,
                                    EquipmentRepository equipmentRepository,
                                    OperatorRepository operatorRepository) {
        this.productionRepository = productionRepository;
        this.equipmentRepository = equipmentRepository;
        this.operatorRepository = operatorRepository;
    }

    @Transactional // Importante para manejar las múltiples inserciones si fallan
    public Long handle(CreateProductionRecordCommand command) {

        // 1. Resolver Equipo (Buscar por ID, si no existe, crear uno básico)
        Equipment equipment = equipmentRepository.findById(command.equipmentId())
                .orElseGet(() -> {
                    // Para el challenge, si no existe el equipo, lo registramos al vuelo
                    return equipmentRepository.save(new Equipment(command.equipmentId(), "Modelo Desconocido"));
                });

        // 2. Resolver Operador (Buscar por Nombre, si no existe, crear)
        Operator operator = operatorRepository.findByFullName(command.operatorName())
                .orElseGet(() -> {
                    return operatorRepository.save(new Operator(command.operatorName()));
                });

        // 3. Crear Registro de Producción vinculando las entidades
        var productionRecord = new ProductionRecord(
                command.date(),
                command.shift(),
                command.zone(),
                command.extractedTons(),
                command.operatingHours(),
                command.fuelConsumption(),
                command.incidents(),
                command.mineralGrade(),
                equipment, // Pasamos el OBJETO Equipment
                operator   // Pasamos el OBJETO Operator
        );

        productionRepository.save(productionRecord);
        return productionRecord.getId();
    }
}
