package br.com.firstclass.spring_boot_essentials.database.repository;

import br.com.firstclass.spring_boot_essentials.database.model.AlunosEntity;
import br.com.firstclass.spring_boot_essentials.database.model.AvaliacoesFisicasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAlunosRepository extends JpaRepository<AlunosEntity, Integer> {

}
