package com.platform.ventistorebackend.mining.domain.model.commands;

import java.util.Date;

/**
 * Command to create a production record.
 * Using Java Record syntax to automatically generate constructor, getters, equals, hashcode, toString.
 */
public record CreateProductionRecordCommand(
        Date date,
        String shift,
        String zone,
        Double extractedTons,
        Double operatingHours,
        Double fuelConsumption,
        Integer incidents,
        Double mineralGrade,
        String equipmentId,
        String operatorName
) {
}
