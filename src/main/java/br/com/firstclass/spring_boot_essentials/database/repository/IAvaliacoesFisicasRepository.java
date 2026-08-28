package br.com.firstclass.spring_boot_essentials.database.repository;

import br.com.firstclass.spring_boot_essentials.database.model.AvaliacoesFisicasEntity;
import br.com.firstclass.spring_boot_essentials.database.model.TreinosEntity;
import dto.AvaliacoesFisicasProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

import java.util.List;

public interface IAvaliacoesFisicasRepository extends JpaRepository<AvaliacoesFisicasEntity, Integer> {

// Paginação

    @NativeQuery(value = """
      SELECT a.id AS id_aluno,
             a.nome AS nome_aluno,
             af.id AS avaliacao,
             af.peso AS peso,
             af.altura AS altura,
             af.percentual_gordura_corporal AS percentual_gordura_corporal
      FROM avaliacoes_fisicas af
      INNER JOIN alunos a 
          ON a.avaliacao_fisica_id = af.id
""")
    List<AvaliacoesFisicasProjection> getAllAvaliacoes();

    @NativeQuery(value = """
      SELECT a.id AS id_aluno,
             a.nome AS nome_aluno,
             af.id AS avaliacao,
             af.peso AS peso,
             af.altura AS altura,
             af.percentual_gordura_corporal AS percentual_gordura_corporal
      FROM avaliacoes_fisicas af
      INNER JOIN alunos a 
          ON a.avaliacao_fisica_id = af.id
    """,
    countQuery = """
      SELECT count(af.id)
      FROM avaliacoes_fisicas af
      INNER JOIN alunos a 
      ON a.avaliacao_fisica_id = af.id  
      """)
    Page<AvaliacoesFisicasProjection> getAllAvaliacoesPageable(Pageable pageable);




}
