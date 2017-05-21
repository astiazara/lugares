package lugares.ag;

import java.util.List;

import org.junit.Test;

import lugares.dominio.Estatisticas;
import lugares.dominio.Lugares;
import lugares.dominio.Pessoa;

public class AlgoritmoGeneticoLugaresTest {

	@Test
	public void test() {
		Lugares lugares = new Lugares(4);
		{
			Pessoa pessoa = new Pessoa(lugares);
			pessoa.setNome("Ana");
			pessoa.setSatisfacao(0, 100);
			pessoa.setSatisfacao(1, 75);
			pessoa.setSatisfacao(2, 50);
			pessoa.setSatisfacao(3, 25);
			lugares.set(pessoa, 0);
		}
		{
			Pessoa pessoa = new Pessoa(lugares);
			pessoa.setNome("Bia");
			pessoa.setSatisfacao(0, 75);
			pessoa.setSatisfacao(1, 100);
			pessoa.setSatisfacao(2, 75);
			pessoa.setSatisfacao(3, 50);
			lugares.set(pessoa, 1);
		}
		{
			Pessoa pessoa = new Pessoa(lugares);
			pessoa.setNome("Caio");
			pessoa.setSatisfacao(0, 50);
			pessoa.setSatisfacao(1, 75);
			pessoa.setSatisfacao(2, 100);
			pessoa.setSatisfacao(3, 75);
			lugares.set(pessoa, 2);
		}
		{
			Pessoa pessoa = new Pessoa(lugares);
			pessoa.setNome("Daniel");
			pessoa.setSatisfacao(0, 0);
			pessoa.setSatisfacao(1, 0);
			pessoa.setSatisfacao(2, 10);
			pessoa.setSatisfacao(3, 0);
			lugares.set(pessoa, 3);
		}
		
		AlgoritmoGeneticoLugares ag = new AlgoritmoGeneticoLugares();
		ag.setPrototipo(lugares);
		
		int geracao = 0;
		int totalSatisfacao = 0;
		Lugares melhor;
		List<Lugares> populacaoAtual = ag.criarPopulacaoRandomica(8);
		do{
			populacaoAtual = ag.rankear(populacaoAtual);
			melhor = populacaoAtual.get(populacaoAtual.size()-1);
			totalSatisfacao = melhor.calcularEstatisticas().getTotalSatisfacao();
			System.out.println("\nGeracao: " + geracao + "\n");
			int i=0;
			for(Lugares individuo : populacaoAtual){
				i++;
				Estatisticas estatisticas = individuo.calcularEstatisticas();
				System.out.println("Individuo " + i);
				System.out.println("Total Satistafacao: " + estatisticas.getTotalSatisfacao());
				System.out.println("Menor Satisfacao: " + estatisticas.getMenorSatisfacao());
				System.out.println("Pontos: " + ag.pontuar(individuo));
				System.out.println();
			}
			
			geracao++;
			populacaoAtual = ag.executar(populacaoAtual);
		} while(totalSatisfacao < 400 && geracao < 100);
		
		System.out.println(">> Melhor <<");
		for (int i = 0; i < melhor.getTotal(); i++) {
			System.out.println("Lugar " + i + ": " + melhor.get(i).getNome() + " " + melhor.get(i).getSatisfacao(i));
		}
	}

}
