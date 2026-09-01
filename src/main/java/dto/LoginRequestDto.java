package dto;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

//lombok


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequestDto {

    @NotBlank(message = "O email não pode estar em branco")
    private String email;
    @NotBlank(message = "A senha não pode estar em branco")
    private String senha;

}
