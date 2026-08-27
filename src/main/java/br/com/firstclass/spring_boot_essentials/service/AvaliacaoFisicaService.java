package br.com.firstclass.spring_boot_essentials.service;

import br.com.firstclass.spring_boot_essentials.database.model.AlunosEntity;
import br.com.firstclass.spring_boot_essentials.database.model.AvaliacoesFisicasEntity;
import br.com.firstclass.spring_boot_essentials.database.model.ExerciciosEntity;
import br.com.firstclass.spring_boot_essentials.database.repository.IAlunosRepository;
import br.com.firstclass.spring_boot_essentials.database.repository.IAvaliacoesFisicasRepository;
import br.com.firstclass.spring_boot_essentials.database.repository.IExerciciosRepository;
import br.com.firstclass.spring_boot_essentials.exception.BadRequestException;
import br.com.firstclass.spring_boot_essentials.exception.NotFoundException;
import dto.AvaliacaoFisicaDto;
import dto.ExercicioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoFisicaService {


    private final IAlunosRepository alunosRepository;
    private final IAvaliacoesFisicasRepository avaliacoesFisicasRepository;

    public void criarAvaliacaoFisica(AvaliacaoFisicaDto avaliacaoFisicaDto) throws NotFoundException, BadRequestException {

        AlunosEntity aluno =  alunosRepository.findById(avaliacaoFisicaDto.getAlunoId())
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        AvaliacoesFisicasEntity avaliacoesFisica = aluno.getAvaliacoesFisicas();

        if(avaliacoesFisica != null){
            throw new BadRequestException("Avaliação física já encontrada");
        }

        avaliacoesFisica = AvaliacoesFisicasEntity.builder()
                .peso(avaliacaoFisicaDto.getPeso())
                .altura(avaliacaoFisicaDto.getAltura())
                .porcentagemGorduraCorporal(BigDecimal.valueOf(avaliacaoFisicaDto.getPorcentagemGorduraCorporal()))
                .build();



        aluno.setAvaliacoesFisicas(avaliacoesFisica);
        alunosRepository.save(aluno); // salvar aluno e avaliação fisica
    }
}

