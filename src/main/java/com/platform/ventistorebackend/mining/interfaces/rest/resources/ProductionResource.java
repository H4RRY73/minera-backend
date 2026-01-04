package com.platform.ventistorebackend.mining.interfaces.rest.resources;

import java.util.Date;

public record ProductionResource(
        Long id,
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
) {}
