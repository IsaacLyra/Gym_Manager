package br.com.firstclass.spring_boot_essentials.database.repository;

import br.com.firstclass.spring_boot_essentials.database.model.AvaliacoesFisicasEntity;
import br.com.firstclass.spring_boot_essentials.database.model.TreinosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAvaliacoesFisicasRepository extends JpaRepository<AvaliacoesFisicasEntity, Integer> {

}
