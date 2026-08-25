package br.com.firstclass.spring_boot_essentials.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "exercicios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class ExerciciosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // chave primaria

    @Column(nullable = false)
    private String nome;

    @Column(name = "grupo_muscular", nullable = false, unique = false)
    private String grupoMuscular;

}
