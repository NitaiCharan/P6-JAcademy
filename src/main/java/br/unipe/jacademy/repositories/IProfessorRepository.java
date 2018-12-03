package br.unipe.jacademy.repositories;

import br.unipe.jacademy.entities.AlunoEntity;
import br.unipe.jacademy.entities.ProfessorEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProfessorRepository extends IGenericRepository<ProfessorEntity>{

    @Query("select s from ProfessorEntity s where s.nome like %?1%")
    List<ProfessorEntity> findProfessoresByName(String nome);
}