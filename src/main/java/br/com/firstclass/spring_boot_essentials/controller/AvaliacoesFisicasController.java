package br.com.firstclass.spring_boot_essentials.controller;

import br.com.firstclass.spring_boot_essentials.database.model.ExerciciosEntity;
import br.com.firstclass.spring_boot_essentials.exception.BadRequestException;
import br.com.firstclass.spring_boot_essentials.exception.NotFoundException;
import br.com.firstclass.spring_boot_essentials.service.AvaliacaoFisicaService;
import br.com.firstclass.spring_boot_essentials.service.ExerciciosService;
import dto.AvaliacaoFisicaDto;
import dto.ExercicioDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/avaliacoes")
@RequiredArgsConstructor
@Validated
public class AvaliacoesFisicasController {

    private final AvaliacaoFisicaService avaliacaoFisicaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public void criarAvaliacoesFisicas(@Valid @RequestBody AvaliacaoFisicaDto avaliacaoFisicaDto) throws NotFoundException, BadRequestException {
        avaliacaoFisicaService.criarAvaliacaoFisica(avaliacaoFisicaDto);

    }


}
