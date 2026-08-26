package br.com.firstclass.spring_boot_essentials.service;

import br.com.firstclass.spring_boot_essentials.database.model.AlunosEntity;
import br.com.firstclass.spring_boot_essentials.database.repository.IAlunosRepository;
import br.com.firstclass.spring_boot_essentials.exception.BadRequestException;
import dto.AlunosDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlunosService {

 private final IAlunosRepository alunosRepository;

 public void criarAluno(AlunosDto alunosDto) throws BadRequestException {

  AlunosEntity aluno = alunosRepository.findByEmail(alunosDto.getEmail())
           .orElse(null);


    if(aluno != null){
     throw new BadRequestException("Aluno já cadastrado");
    }

    alunosRepository.save(AlunosEntity.builder()
            .nome(alunosDto.getNome())
           .email(alunosDto.getEmail())
            .build());



 }


}

