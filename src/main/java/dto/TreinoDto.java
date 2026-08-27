package dto;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

//lombok
@Entity
@Table(name = "exercicio")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TreinoDto {

    @NotNull // NULA E NEM VAZIA
    private Integer alunoId;
    @NotNull
    private String nome;
    @NotEmpty //Impossível criar um treino sem exercicios
    private List<Integer> exerciciosIds;






}
