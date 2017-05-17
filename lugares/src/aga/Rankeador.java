package aga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Rankeador<T> {

	private class RankItem implements Comparable<RankItem> {
		private T item;
		private int pontuacao;

		public RankItem(T item, int pontuacao) {
			this.item = item;
			this.pontuacao = pontuacao;
		}

		@Override
		public int compareTo(Rankeador<T>.RankItem o) {
			return Integer.compare(pontuacao, o.pontuacao);
		}
	}
	
	private List<RankItem> itens = new ArrayList<RankItem>();
	
	
	public void adicionar(T item, int pontuacao){
		itens.add(new RankItem(item, pontuacao));
	}
	
	public List<T> rankear(){
		Collections.sort(itens);
		List<T> ordenados = new ArrayList<T>(itens.size());
		for(RankItem rankItem : itens){
			ordenados.add(rankItem.item);
		}
		return ordenados;
	}
	
}
