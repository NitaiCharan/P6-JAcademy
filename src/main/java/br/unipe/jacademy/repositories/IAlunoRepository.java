package br.unipe.jacademy.repositories;

import br.unipe.jacademy.entities.AlunoEntity;
import br.unipe.jacademy.entities.SalaEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAlunoRepository extends IGenericRepository<AlunoEntity>{

    @Query("select s from AlunoEntity s where s.nome like %?1%")
    List<AlunoEntity> findAlunosByName(String nome);
}