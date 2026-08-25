package br.com.firstclass.spring_boot_essentials.database.repository;

import br.com.firstclass.spring_boot_essentials.database.model.ExerciciosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Native;
import java.util.List;

public interface IExerciciosRepository extends JpaRepository<ExerciciosEntity, Integer> {

    List<ExerciciosEntity> findAllByGrupoMuscular(String grupoMuscular); //QUERY SEM ESCREVER SQL

    @Query(value = """
        SELECT e 
        FROM ExerciciosEntity e
        WHERE UPPER (e.grupoMuscular) = UPPER(:grupoMuscular)
        """) //JPQL
    List<ExerciciosEntity> findAllByGrupoMuscularJpql(@Param("grupoMuscular") String grupoMuscular);

    @NativeQuery(value = """
        SELECT e 
        FROM exercicios e 
        WHERE UPPER (e.grupo_muscular) = UPPER(:grupoMuscular)
""")
    List<ExerciciosEntity> findAllByGrupoMuscularNative(String grupoMuscular);
}
