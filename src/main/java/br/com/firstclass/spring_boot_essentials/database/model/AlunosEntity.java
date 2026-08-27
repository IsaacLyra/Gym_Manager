package br.com.firstclass.spring_boot_essentials.database.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "alunos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class AlunosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String email;

//O PADRÃO DO OneToOne é EAGER
@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // alterações em cascata  EAGER VS LAZY CARREGAMENTO RAPIDO E CARREGAMENTO LENTO
@JoinColumn(name = "avaliacao_fisica_id") //nome da fk
private AvaliacoesFisicasEntity avaliacoesFisicas;

//O PADRÃO DO OneToMany é LAZY
@OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY)
private Set<TreinosEntity> treinos = new HashSet<>();
}
