package br.unipe.jacademy.entities;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Super;

import java.util.Set;

@Entity
@Table(name = "Professores")
public class ProfessorEntity extends PessoaEntity {
    private static final long serialVersionUID = 6L;
    
    @Transient
    private PessoaEntity pessoaEntity;

    @OneToMany(mappedBy = "professor")
    private Set<TurmaEntity> turmas;

	public ProfessorEntity(PessoaEntity pessoaEntity) {
		this.pessoaEntity = pessoaEntity;
	}

	public ProfessorEntity() {
	}
	
	public void printPrivilegio() {
		super.printPrivilegio();
		System.out.println(this.privilegio);
	}
}
