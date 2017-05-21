package lugares.ag;

import java.util.ArrayList;
import java.util.List;

import aga.Rand;
import aga.abstrato.AlgoritmoGeneticoAbstrato;
import lugares.dominio.Estatisticas;
import lugares.dominio.Lugares;
import lugares.dominio.Pessoa;

public class AlgoritmoGeneticoLugares extends AlgoritmoGeneticoAbstrato<Lugares> {

	private Lugares prototipo;
	
	@Override
	protected Lugares criarIndividuoRandomico() {
		Lugares randomico = new Lugares(prototipo.getTotal());
		List<Pessoa> pessoas = prototipo.obterPessoas();
		for (int i = 0; i < randomico.getTotal(); i++) {
			int indicePessoa = Rand.random.nextInt(pessoas.size());
			randomico.set(pessoas.get(indicePessoa), i);
			pessoas.remove(indicePessoa);
		}
		return randomico;
	}

	@Override
	protected Lugares cruzar(Lugares pai, Lugares mae) {
		Lugares filho = new Lugares(pai.getTotal());
		int pontoCorte = Rand.random.nextInt(pai.getTotal()-1);
		
		List<Pessoa> sobrasPai = new ArrayList<Pessoa>(pai.getTotal() - pontoCorte);
		for (int i = 0; i < pai.getTotal(); i++) {
			Pessoa genePai = pai.get(i);
			if(i <= pontoCorte){
				filho.set(genePai, i);
			} else {
				sobrasPai.add(genePai);
			}
		}
		
		for (int i = pontoCorte + 1; i < pai.getTotal(); i++) {
			Pessoa geneMae = mae.get(i);
			if(!filho.contem(geneMae)){
				filho.set(geneMae, i);
				sobrasPai.remove(geneMae);
			}
		}
		
		if(!sobrasPai.isEmpty()){
			for (int i = pontoCorte + 1; i < pai.getTotal(); i++) {
				if(filho.ehVago(i)){
					filho.set(sobrasPai.get(0), i);
					sobrasPai.remove(0);
				}
			}
		}
		
		return filho;
	}

	@Override
	public int pontuar(Lugares individuo) {
		int maximaSatisfacaoPossivel = individuo.getTotal() * Pessoa.MAIOR_SATISFACAO;
		Estatisticas estatisticas = individuo.calcularEstatisticas();
		int pontosMaximizarMenorSatifacao = estatisticas.getMenorSatisfacao() * maximaSatisfacaoPossivel;
		return pontosMaximizarMenorSatifacao + estatisticas.getTotalSatisfacao();
	}

	@Override
	protected void mutar(Lugares filho) {
		int indicePessoa1 = Rand.random.nextInt(filho.getTotal());
		Pessoa pessoa1 = filho.get(indicePessoa1);
		filho.remover(indicePessoa1);
		
		int indicePessoa2;
		do{
			indicePessoa2 = Rand.random.nextInt(filho.getTotal());
		} while(indicePessoa2 == indicePessoa1);
			
		Pessoa pessoa2 = filho.get(indicePessoa2);
		filho.remover(indicePessoa2);
		
		filho.set(pessoa1, indicePessoa2);
		filho.set(pessoa2, indicePessoa1);
	}

	public Lugares getPrototipo() {
		return prototipo;
	}
	public void setPrototipo(Lugares prototipo) {
		this.prototipo = prototipo;
	}

}
