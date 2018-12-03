package br.unipe.jacademy.repositories;

import br.unipe.jacademy.entities.EnderecoEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEnderecoRepository extends IGenericRepository<EnderecoEntity>{

    @Query("select e from EnderecoEntity e where e.pessoa = ?1")
    public List<EnderecoEntity> getEnderecoById(Long alunoid);
}