package com.platform.ventistorebackend.mining.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "operadores")
@Getter
@Setter
@NoArgsConstructor
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    @Column(name = "operador_id")
    private Long id;

    @Column(name = "nombre_completo", unique = true, nullable = false, length = 100)
    private String fullName;

    public Operator(String fullName) {
        this.fullName = fullName;
    }
}
