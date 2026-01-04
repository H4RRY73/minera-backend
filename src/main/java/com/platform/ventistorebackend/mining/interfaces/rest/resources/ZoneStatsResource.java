package com.platform.ventistorebackend.mining.interfaces.rest.resources;

public record ZoneStatsResource(
        String zone,
        Double totalTons,
        Double avgGrade,
        Long totalIncidents
) {}
