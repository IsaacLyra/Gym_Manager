package br.com.firstclass.spring_boot_essentials.service;

import br.com.firstclass.spring_boot_essentials.config.TokenProvider;
import br.com.firstclass.spring_boot_essentials.database.model.AlunosEntity;
import br.com.firstclass.spring_boot_essentials.database.model.RolesEntity;
import br.com.firstclass.spring_boot_essentials.database.repository.IAlunosRepository;
import br.com.firstclass.spring_boot_essentials.database.repository.IRolesRepository;
import br.com.firstclass.spring_boot_essentials.enums.RoleTypeEnum;
import br.com.firstclass.spring_boot_essentials.exception.BadRequestException;
import dto.LoginRequestDto;
import dto.RegisterRequestDto;
import dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final IAlunosRepository alunosRepository;
    private final IRolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private Long expirationTime;


    public void Register(RegisterRequestDto dto) throws BadRequestException {

        AlunosEntity aluno = alunosRepository.findByEmail(dto.getEmail())
                .orElse(null);


        if(aluno != null){
            throw new BadRequestException("Aluno já cadastrado com este email");
        }

        RolesEntity role = rolesRepository.findByNome(RoleTypeEnum.ALUNO.name())
                        .orElseGet(() -> rolesRepository.save(RolesEntity.builder()
                                        .nome(RoleTypeEnum.ALUNO.name())
                                .build()));

        alunosRepository.save(AlunosEntity.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .roles(Set.of(role))
                .senha(passwordEncoder.encode(dto.getSenha()))
                .build());

    }


    public TokenResponseDto login (LoginRequestDto dto) throws Exception{

        try{
           Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha()));
            String token = tokenProvider.gerarToken(authentication);
            //authentication provider -> userdetailservice -> passwordEnconder.matches(): comparar senha passsada por dto com a senha que esta no banco de dados
        return new TokenResponseDto(token, expirationTime);
        }
        catch (BadCredentialsException e){
            throw new BadRequestException("Credenciais inválidas: ");
        }
        catch (Exception e){
            throw e;
        }
    }

}
