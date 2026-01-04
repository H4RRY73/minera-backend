package com.platform.ventistorebackend.mining.interfaces.rest.transform;

import com.platform.ventistorebackend.mining.domain.model.aggregates.ProductionRecord;
import com.platform.ventistorebackend.mining.domain.model.commands.CreateProductionRecordCommand;
import com.platform.ventistorebackend.mining.interfaces.rest.resources.CreateProductionResource;
import com.platform.ventistorebackend.mining.interfaces.rest.resources.ProductionResource;

public class ProductionResourceFromEntityAssembler {
    public static ProductionResource toResourceFromEntity(ProductionRecord entity) {
        return new ProductionResource(
                entity.getId(),
                entity.getDate(),
                entity.getShift(),
                entity.getZone(),
                entity.getExtractedTons(),
                entity.getOperatingHours(),
                entity.getFuelConsumption(),
                entity.getIncidents(),
                entity.getMineralGrade(),
                entity.getEquipment().getEquipmentId(), // Accedemos a la relación
                entity.getOperator().getFullName()      // Accedemos a la relación
        );
    }

    public static CreateProductionRecordCommand toCommandFromResource(CreateProductionResource resource) {
        return new CreateProductionRecordCommand(
                resource.date(), resource.shift(), resource.zone(),
                resource.extractedTons(), resource.operatingHours(), resource.fuelConsumption(),
                resource.incidents(), resource.mineralGrade(),
                resource.equipmentId(), resource.operatorName()
        );
    }
}
