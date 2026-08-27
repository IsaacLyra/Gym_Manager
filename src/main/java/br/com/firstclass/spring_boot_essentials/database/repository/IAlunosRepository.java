package br.com.firstclass.spring_boot_essentials.database.repository;

import br.com.firstclass.spring_boot_essentials.database.model.AlunosEntity;
import br.com.firstclass.spring_boot_essentials.database.model.AvaliacoesFisicasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IAlunosRepository extends JpaRepository<AlunosEntity, Integer> {

    Optional<AlunosEntity> findByEmail(String email);// retorna 1 registro pode existir ou não


    @Query(value = "SELECT a FROM AlunosEntity a JOIN FETCH a.avaliacoesFisicas")//JPQL
    Optional<AlunosEntity>FindByIdFetch(Integer alunoId);



}
