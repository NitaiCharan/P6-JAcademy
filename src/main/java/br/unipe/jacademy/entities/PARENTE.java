package br.unipe.jacademy.entities;

public class PARENTE extends AlunoEntity{

	private PessoaEntity pessoaEntity;
	
	public void printPrivilegio() {
		super.printPrivilegio();
		System.out.println(this.privilegio);
	}
}
