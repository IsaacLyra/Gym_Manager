package br.com.firstclass.spring_boot_essentials.database.model;


import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RolesEntity implements GrantedAuthority {

    //Papeis que um usuario pode ter na aplicacao
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome; // nome das autoridades




    @Override
    public @Nullable String getAuthority() {
        return nome;
    }
}
