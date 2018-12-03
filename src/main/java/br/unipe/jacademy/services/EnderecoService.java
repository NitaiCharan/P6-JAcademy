package br.unipe.jacademy.services;

import br.unipe.jacademy.entities.EnderecoEntity;
import br.unipe.jacademy.entities.TurmaEntity;
import br.unipe.jacademy.repositories.IEnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService extends GenericService<EnderecoEntity, IEnderecoRepository> {

    @Autowired
    protected EnderecoService(IEnderecoRepository repository) {
        super(repository);
    }

    public List<EnderecoEntity> getEnderecoId(Long id){
        return super.repository.getEnderecoById(id);
    }
}
