package aga.abstrato;

import java.util.ArrayList;
import java.util.List;

import aga.Rand;
import aga.Rankeador;
import aga.Roleta;

public abstract class AlgoritmoGeneticoAbstrato<T> {

	private int tamanhoElite = 1;
	private int tamanhoEstrangeiros = 1;
	private int percentualChanceMutacao = 1;

	public List<T> criarPopulacaoRandomica(int tamanhoPopulacao) {
		List<T> populacao = new ArrayList<T>(tamanhoPopulacao);
		for (int i = 0; i < tamanhoPopulacao; i++) {
			populacao.add(criarIndividuoRandomico());
		}
		return populacao;
	}

	protected abstract T criarIndividuoRandomico();

	public List<T> executar(List<T> populacaoAtual){
		validarConfiguracoes(populacaoAtual.size());
		
		List<T> populacaoNova = new ArrayList<T>(populacaoAtual.size());
		List<T> rank = rankear(populacaoAtual);
		populacaoNova.addAll(criarElite(rank));
		populacaoNova.addAll(criarEstrangeiros());
		populacaoNova.addAll(criarFilhos(rank));
		
		if(populacaoNova.size() != populacaoAtual.size()){
			System.out.println("Ops!");
		}
		
		return populacaoNova;
	}

	private void validarConfiguracoes(int tamanhoPopulacao) {
		if(tamanhoPopulacao < 2){
			throw new IllegalArgumentException("Tamanho da Populacao < 2");
		}
		
		if((tamanhoElite + tamanhoEstrangeiros) > tamanhoPopulacao){
			throw new IllegalArgumentException("Tamanho da Elite + Tamanho de Estrangeiros > Tamanho da Populacao");
		}
	}

	private List<T> criarElite(List<T> rank) {
		List<T> elite = new ArrayList<T>(tamanhoElite);
		for (int i = 0; i < tamanhoElite; i++) {
			elite.add(rank.get(rank.size()-1-i));
		}
		return elite;
	}
	
	private List<T> criarEstrangeiros() {
		return criarPopulacaoRandomica(tamanhoEstrangeiros);
	}

	private List<T> criarFilhos(List<T> rank) {
		int totalFilhos = rank.size() - tamanhoElite - tamanhoEstrangeiros;
		List<T> filhos = new ArrayList<T>(totalFilhos);		
		Roleta<T> roletaPais = criarRoletaPais(rank);
		for (int i = 0; i < totalFilhos; i++) {
			filhos.add(criarFilho(roletaPais));
		}
		return filhos;
	}
	
	private T criarFilho(Roleta<T> roletaPais) {
		T pai = roletaPais.girar();
		T mae = roletaPais.girar();
		T filho = cruzar(pai, mae);
		if(deveMutar()){
			mutar(filho);
		}
		return filho;
	}

	private boolean deveMutar() {
		if(percentualChanceMutacao == 0){
			return false;
		} else {
			return (Rand.random.nextInt(100/percentualChanceMutacao) == 0);
		}
	}
	
	protected abstract T cruzar(T pai, T mae);
	
	protected abstract void mutar(T filho);
	
	private Roleta<T> criarRoletaPais(List<T> rank) {
		Roleta<T> roleta = new Roleta<T>();
		for(int i=0; i < rank.size(); i++){
			roleta.adicionar(rank.get(i), i+1);
		}
		return roleta;
	}

	public List<T> rankear(List<T> populacao) {
		Rankeador<T> rankeador = new Rankeador<T>(populacao.size());
		for(T individuo : populacao){
			rankeador.adicionar(individuo, pontuar(individuo));
		}
		return rankeador.rankear();
	}
	
	public abstract int pontuar(T individuo);

	public int getTamanhoElite() {
		return tamanhoElite;
	}
	public void setTamanhoElite(int tamanhoElite) {
		if(tamanhoElite < 0){
			throw new IllegalArgumentException("Tamanho da Elite deve ser igual ou maior que zero.");
		}
		this.tamanhoElite = tamanhoElite;
	}

	public int getTamanhoEstrangeiros() {
		return tamanhoEstrangeiros;
	}
	public void setTamanhoEstrangeiros(int tamanhoEstrangeiros) {
		if(tamanhoEstrangeiros < 0){
			throw new IllegalArgumentException("Tamanho de Estrangeiros deve ser igual ou maior que zero.");
		}
		this.tamanhoEstrangeiros = tamanhoEstrangeiros;
	}

	public int getPercentualChanceMutacao() {
		return percentualChanceMutacao;
	}
	public void setPercentualChanceMutacao(int percentualChanceMutacao) {
		if(percentualChanceMutacao < 0 || percentualChanceMutacao > 100){
			throw new IllegalArgumentException("Percentual de chance de mutacao deve estar entre 0 e 100 inclusives.");
		}
		this.percentualChanceMutacao = percentualChanceMutacao;
	}
	
}
