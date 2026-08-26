package br.com.firstclass.spring_boot_essentials.controller;

import br.com.firstclass.spring_boot_essentials.database.model.ExerciciosEntity;
import br.com.firstclass.spring_boot_essentials.exception.BadRequestException;
import br.com.firstclass.spring_boot_essentials.service.AlunosService;
import br.com.firstclass.spring_boot_essentials.service.ExerciciosService;
import dto.AlunosDto;
import dto.ExercicioDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/alunos")
@RequiredArgsConstructor
@Validated
public class AlunosController {

    private final AlunosService alunosService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public void criarAluno(@Valid @RequestBody AlunosDto alunosDto) throws BadRequestException {
        alunosService.criarAluno(alunosDto);
    }

}
