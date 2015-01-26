package br.com.leonardo.estudos.restservlet.servlet;

import java.util.ArrayList;
import java.util.List;

import br.com.leonardo.estudos.restservlet.model.Pessoa;
import br.com.leonardo.estudos.restservlet.model.Pessoas;

public class PessoaRepo {

	private static List<Pessoa> pessoasRepo = new ArrayList<>();
	
//	static {
//		hashSetPessoas.add(new Pessoa("12345678910", "Andre", "Barros"));
//		hashSetPessoas.add(new Pessoa("45612378910", "Carlos", "D'avila"));
//		hashSetPessoas.add(new Pessoa("45678912310", "Enzo", "Fernandes"));
//		hashSetPessoas.add(new Pessoa("45678910123", "Gustavo", "Henrique"));
//		hashSetPessoas.add(new Pessoa("78910123456", "Ivan", "Jose"));
//	}
	
	public Pessoas find(String cpf) {
		Pessoas pessoas = new Pessoas();
		if(cpf != null) {
			Pessoa pessoa = new Pessoa();
			pessoa.setCpf(cpf);
			pessoas.setPessoas(new ArrayList<Pessoa>());
			int i = pessoasRepo.indexOf(pessoa);
			if(i != -1) {
				pessoas.getPessoas().add(pessoasRepo.get(i));
			}
		} else {
			pessoas.setPessoas(pessoasRepo);
		}
				
		return pessoas;
	}
	
	public boolean insert(Pessoa p) {
		boolean retorno = false; 
		if(!pessoasRepo.contains(p)) {
			p.setId(pessoasRepo.size());
			pessoasRepo.add(p);
			retorno = true;
		}
		return retorno;
	}
	
	public boolean update(Pessoa pessoa)
	{
		boolean retorno = false;
		Pessoa p;
		if(pessoasRepo.contains(pessoa)) {
			while((p = pessoasRepo.iterator().next()) != null) {
				if(p.equals(pessoa)){
					pessoasRepo.add(p);
					break;
				}
			}
			retorno = true;
		}
		return retorno;
	}
	
	
}
