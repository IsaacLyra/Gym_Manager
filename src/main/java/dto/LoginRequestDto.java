package dto;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
public class LoginRequestDto {

    @NotBlank // NULA E NEM VAZIA
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;

}
