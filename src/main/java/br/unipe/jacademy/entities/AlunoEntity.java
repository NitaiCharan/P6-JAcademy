package br.unipe.jacademy.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Alunos")
public class AlunoEntity extends PessoaEntity {
    private static final long serialVersionUID = 2L;

    @ManyToMany
    private Set<TurmaEntity> turmas;
    
    @Transient
    private PessoaEntity pessoaEntity;

	public AlunoEntity() {
	}
	public AlunoEntity(PessoaEntity pessoaEntity) {
		this.pessoaEntity = pessoaEntity;
	}

	public void printPrivilegio() {
		super.printPrivilegio();
		System.out.println(this.privilegio);
	}
}