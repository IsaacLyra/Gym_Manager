package br.com.firstclass.spring_boot_essentials.service;

import br.com.firstclass.spring_boot_essentials.database.model.AlunosEntity;
import br.com.firstclass.spring_boot_essentials.database.model.AvaliacoesFisicasEntity;
import br.com.firstclass.spring_boot_essentials.database.model.TreinosEntity;
import br.com.firstclass.spring_boot_essentials.database.repository.IAlunosRepository;
import br.com.firstclass.spring_boot_essentials.database.repository.IAvaliacoesFisicasRepository;
import br.com.firstclass.spring_boot_essentials.database.repository.ITreinosRepository;
import br.com.firstclass.spring_boot_essentials.exception.BadRequestException;
import br.com.firstclass.spring_boot_essentials.exception.NotFoundException;
import dto.AlunosDto;
import dto.AvaliacoesFisicasProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunosService {

 private final ITreinosRepository treinosRepository;
 private final IAvaliacoesFisicasRepository avaliacoesFisicasRepository;
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

  public AvaliacoesFisicasEntity getAlunoAvaliacao(Integer alunoId) throws NotFoundException {
   AlunosEntity aluno = alunosRepository.findById(alunoId)
           .orElseThrow(() -> new NotFoundException("Aluno não econtrado")); //Tem que retornar um dto não um entity

      AvaliacoesFisicasEntity avaliacoesFisicas = aluno.getAvaliacoesFisicas();
     if(avaliacoesFisicas == null){
      throw new NotFoundException("Avaliacao fisica não encontrada");
     }

     return avaliacoesFisicas;
  }

  @Transactional(rollbackFor = Exception.class)
    public void DeletarAluno(Integer alunoId) throws NotFoundException {

    //transaction begin

    AlunosEntity aluno =   alunosRepository.findById(alunoId)
             .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        //1.Deletar treinos do aluno
        List<Integer> treinoAlunoIds = aluno.getTreinos().stream().map(TreinosEntity::getId).toList();

        treinosRepository.deleteAllById(treinoAlunoIds);

        //2. deletar o aluno
        treinosRepository.deleteById(alunoId);

        // Rollback caso de erro inesperado

        //Deletar avaliação física
        avaliacoesFisicasRepository.deleteById(aluno.getAvaliacoesFisicas().getId());

    // transaction commit


    }


}

