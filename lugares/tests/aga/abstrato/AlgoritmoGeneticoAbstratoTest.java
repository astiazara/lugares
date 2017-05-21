package aga.abstrato;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AlgoritmoGeneticoAbstratoTest {

	@Test
	public void test() {
		AlgoritmoGeneticoTeste ag = new AlgoritmoGeneticoTeste();
		ag.setTamanhoEstrangeiros(1);
		ag.setTamanhoElite(0);
		
		int melhorPontuacao = 0;
		int geracao = 0;
		List<String> populacaoAtual = new ArrayList<String>(5);
		populacaoAtual.add("xxxx");
		populacaoAtual.add("axxx");
		populacaoAtual.add("xmxx");
		populacaoAtual.add("xxox");
		populacaoAtual.add("xxxr");
		
		do{
			List<String> rank = ag.rankear(populacaoAtual);
			System.out.println("\nGeracao " + geracao);
			for(String palavra : rank){
				System.out.println(palavra + " " + ag.pontuar(palavra));
			}
			melhorPontuacao = ag.pontuar(rank.get(rank.size()-1));
			
			geracao++;
			populacaoAtual = ag.executar(populacaoAtual);
		} while(melhorPontuacao != 4 && geracao < 1000);
	}

}
