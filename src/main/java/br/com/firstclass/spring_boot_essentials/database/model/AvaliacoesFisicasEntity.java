package br.com.firstclass.spring_boot_essentials.database.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.cache.internal.StrategyCreatorRegionFactoryImpl;

import java.math.BigDecimal;

@Entity
@Table(name = "avaliacoes_fisicas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class AvaliacoesFisicasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private BigDecimal peso;

    @Column(nullable = false)
    private BigDecimal altura;

    @Column(name = "percentual_gordura_corporal", nullable = false)
    private BigDecimal porcentagemGorduraCorporal;



}
