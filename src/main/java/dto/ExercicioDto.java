package dto;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
//lombok
@Entity
@Table(name = "exercicio")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExercicioDto {

    @NotBlank // NULA E NEM VAZIA
    private String nome;
    @NotBlank
    private String grupoMuscular;

}
