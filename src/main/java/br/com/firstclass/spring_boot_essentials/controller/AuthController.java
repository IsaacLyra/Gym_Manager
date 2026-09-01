package br.com.firstclass.spring_boot_essentials.controller;

import br.com.firstclass.spring_boot_essentials.exception.BadRequestException;
import br.com.firstclass.spring_boot_essentials.service.AuthenticationService;
import dto.LoginRequestDto;
import dto.RegisterRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    // ALTERAÇÃO 3: Retornando ResponseEntity para definir o HTTP Status 201 Created
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequestDto registerRequestDto) throws BadRequestException {
        authenticationService.Register(registerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    // ALTERAÇÃO 4: Retornando ResponseEntity contendo a resposta (ex: Token) ou OK 200
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDto loginRequestDto) throws Exception {
        var token = authenticationService.login(loginRequestDto);
        return ResponseEntity.ok(token); // Ou ResponseEntity.ok().build() se o método de service for void
    }
}

