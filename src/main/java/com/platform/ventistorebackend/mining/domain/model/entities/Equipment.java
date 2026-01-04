package com.platform.ventistorebackend.mining.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "equipos") // Mapeo exacto a tu tabla SQL
@Getter
@Setter
@NoArgsConstructor
public class Equipment {

    @Id
    @Column(name = "equipo_id", length = 20)
    private String equipmentId; // El ID manual (ej: EQ-001)

    @Column(name = "modelo", length = 50)
    private String model;

    public Equipment(String equipmentId, String model) {
        this.equipmentId = equipmentId;
        this.model = model;
    }
}
