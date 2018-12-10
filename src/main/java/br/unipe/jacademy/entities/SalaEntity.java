package br.unipe.jacademy.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Salas")
public class SalaEntity extends GenericEntity implements Observado {
    private static final long serialVersionUID = 7L;

    @OneToMany(mappedBy = "sala", cascade=CascadeType.PERSIST)
    private Set<TurmaEntity> turmas;
    
    //@NotBlank(message = "Campo deve ser preenchido")
    private String nome;

    //@Min(value=0, message="Disponibilidade inválida")
    private int disponibilidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(int disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public void excluirTurma(TurmaEntity turma) {
    	turmas.remove(turma);
    }
    
    public void addTurma(TurmaEntity turma) {
    	notificar();
    	turmas.add(turma); 
	}
	@Override
	public void addObservador() {
	}

	@Override
	public void excluirObservador() {
	}

	@Override
	public void notificar() {
		turmas.forEach(turma -> {
			turma.update(this,toString()+" reservado para "+turma.toString());
		});
	}

	@Override
	public String toString() {
		return "SalaEntity [nome=" + nome + ", disponibilidade=" + disponibilidade + "]";
	}
}