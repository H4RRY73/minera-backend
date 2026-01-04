package com.platform.ventistorebackend.mining.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Date;

public record CreateProductionResource(
        @NotNull Date date,
        @NotNull String shift, // "Día" o "Noche"
        @NotNull String zone,
        @Positive Double extractedTons,
        @Positive Double operatingHours,
        @Positive Double fuelConsumption,
        @NotNull Integer incidents,
        @Positive Double mineralGrade,
        @NotNull String equipmentId, // ID Manual (ej: EQ-001)
        @NotNull String operatorName // Nombre para buscar o crear
) {}
