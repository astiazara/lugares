package aga.abstrato;

import java.util.ArrayList;
import java.util.List;

public abstract class AlgoritmoGeneticoAbstrato<T> {

	public List<T> criarPopulacaoRandomica(int tamanhoPopulacao) {
		List<T> populacao = new ArrayList<T>(tamanhoPopulacao);
		for (int i = 0; i < tamanhoPopulacao; i++) {
			populacao.add(criarIndividuoRandomico());
		}
		return populacao;
	}

	protected abstract T criarIndividuoRandomico();

	public List<T> executar(List<T> populacaoAtual){
		
	}
	
}
