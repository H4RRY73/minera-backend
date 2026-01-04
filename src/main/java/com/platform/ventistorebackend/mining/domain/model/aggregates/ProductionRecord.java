package com.platform.ventistorebackend.mining.domain.model.aggregates;

import com.platform.ventistorebackend.mining.domain.model.entities.Equipment;
import com.platform.ventistorebackend.mining.domain.model.entities.Operator;
import com.platform.ventistorebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "produccion")
@Getter
@Setter
@NoArgsConstructor
public class ProductionRecord extends AuditableAbstractAggregateRoot<ProductionRecord> {

    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    // Mapeamos el ENUM de MySQL
    @Column(name = "turno", nullable = false)
    private String shift;

    @Column(name = "zona", length = 20)
    private String zone;

    @Column(name = "toneladas_extraidas")
    private Double extractedTons;

    @Column(name = "horas_operativas")
    private Double operatingHours;

    @Column(name = "consumo_combustible")
    private Double fuelConsumption;

    @Column(name = "incidentes")
    private Integer incidents;

    @Column(name = "ley_mineral")
    private Double mineralGrade;

    // --- RELACIONES (Foreign Keys) ---

    @ManyToOne(fetch = FetchType.EAGER) // O LAZY según prefieras
    @JoinColumn(name = "equipo_id", nullable = false) // FK hacia Equipos
    private Equipment equipment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "operador_id", nullable = false) // FK hacia Operadores
    private Operator operator;

    public ProductionRecord(Date date, String shift, String zone, Double extractedTons,
                            Double operatingHours, Double fuelConsumption, Integer incidents,
                            Double mineralGrade, Equipment equipment, Operator operator) {
        this.date = date;
        this.shift = shift;
        this.zone = zone;
        this.extractedTons = extractedTons;
        this.operatingHours = operatingHours;
        this.fuelConsumption = fuelConsumption;
        this.incidents = incidents;
        this.mineralGrade = mineralGrade;
        this.equipment = equipment;
        this.operator = operator;
    }
}
