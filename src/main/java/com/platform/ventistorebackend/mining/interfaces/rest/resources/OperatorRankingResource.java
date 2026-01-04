package com.platform.ventistorebackend.mining.interfaces.rest.resources;

public record OperatorRankingResource(
        String operatorName,
        Double totalTons
) {}
