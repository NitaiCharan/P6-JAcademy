package br.unipe.jacademy.repositories;

import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.unipe.jacademy.entities.GenericEntity;

@Repository
@Transactional
@Scope("singleton")
public interface IGenericRepository<Entity extends GenericEntity> extends CrudRepository<Entity, Long> {
}