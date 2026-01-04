package com.platform.ventistorebackend.mining.interfaces.rest;

import com.platform.ventistorebackend.mining.application.internal.commandservices.MiningCommandServiceImpl;
import com.platform.ventistorebackend.mining.application.internal.queryservices.MiningQueryServiceImpl;
import com.platform.ventistorebackend.mining.interfaces.rest.resources.CreateProductionResource;
import com.platform.ventistorebackend.mining.interfaces.rest.resources.OperatorRankingResource;
import com.platform.ventistorebackend.mining.interfaces.rest.resources.ProductionResource;
import com.platform.ventistorebackend.mining.interfaces.rest.resources.ZoneStatsResource;
import com.platform.ventistorebackend.mining.interfaces.rest.transform.ProductionResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/production", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Production", description = "Mining Production Management Endpoints")
public class ProductionController {

    private final MiningCommandServiceImpl commandService;
    private final MiningQueryServiceImpl queryService;

    public ProductionController(MiningCommandServiceImpl commandService, MiningQueryServiceImpl queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    // 1. GET - Listar todos con paginación
    @GetMapping
    @Operation(summary = "Get all production records with pagination")
    public ResponseEntity<Page<ProductionResource>> getAllProduction(Pageable pageable) {
        var page = queryService.getAllProduction(pageable)
                .map(ProductionResourceFromEntityAssembler::toResourceFromEntity);
        return ResponseEntity.ok(page);
    }

    // 2. GET - Obtener producción de un equipo específico
    @GetMapping("/{equipmentId}")
    @Operation(summary = "Get production records by equipment ID")
    public ResponseEntity<List<ProductionResource>> getByEquipment(@PathVariable String equipmentId) {
        var records = queryService.getProductionByEquipment(equipmentId);
        var resources = records.stream()
                .map(ProductionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    // 3. GET - Estadísticas de una zona
    @GetMapping("/stats/zone/{name}")
    @Operation(summary = "Get statistics for a specific zone")
    public ResponseEntity<ZoneStatsResource> getZoneStats(@PathVariable String name) {
        List<Object[]> stats = queryService.getStatsByZone(name);

        if (stats.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Mapeo seguro de Object[] a Record
        Object[] row = stats.get(0);
        var resource = new ZoneStatsResource(
                (String) row[0],                            // zone
                ((Number) row[1]).doubleValue(),            // totalTons (suma)
                ((Number) row[2]).doubleValue(),            // avgGrade (promedio)
                ((Number) row[3]).longValue()               // totalIncidents (suma)
        );
        return ResponseEntity.ok(resource);
    }

    // 4. GET - Ranking Top Operadores
    @GetMapping("/ranking/operators")
    @Operation(summary = "Get top operators by productivity")
    public ResponseEntity<List<OperatorRankingResource>> getOperatorRanking() {
        List<Object[]> ranking = queryService.getTopOperators();

        var resources = ranking.stream()
                .limit(5) // Requerimiento: Top 5
                .map(row -> new OperatorRankingResource(
                        (String) row[0],                    // operatorName
                        ((Number) row[1]).doubleValue()     // totalTons
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    // 5. POST - Crear nuevo registro
    @PostMapping
    @Operation(summary = "Create a new production record")
    public ResponseEntity<Long> createRecord(@RequestBody CreateProductionResource resource) {
        var command = ProductionResourceFromEntityAssembler.toCommandFromResource(resource);
        Long id = commandService.handle(command);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
