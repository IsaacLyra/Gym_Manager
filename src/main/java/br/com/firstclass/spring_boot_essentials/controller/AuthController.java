package br.com.firstclass.spring_boot_essentials.controller;

import br.com.firstclass.spring_boot_essentials.exception.BadRequestException;
import br.com.firstclass.spring_boot_essentials.service.AuthenticationService;
import dto.LoginRequestDto;
import dto.RegisterRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public  void register(@RequestBody  @Valid RegisterRequestDto registerRequestDto) throws BadRequestException {
        authenticationService.Register(registerRequestDto);


    }

    @PostMapping("/login")
    public  void login(@RequestBody  @Valid LoginRequestDto loginRequestDto) throws Exception {
        authenticationService.login(loginRequestDto);


    }
}

