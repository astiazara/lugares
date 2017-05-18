package lugares.ag;

import java.util.List;

import lugares.dominio.Lugares;
import lugares.dominio.Pessoa;
import aga.Rand;
import aga.abstrato.AlgoritmoGeneticoAbstrato;

public class AlgoritmoGeneticoLugares extends AlgoritmoGeneticoAbstrato<Lugares> {

	private Lugares prototipo;
	
	@Override
	protected Lugares criarIndividuoRandomico() {
		Lugares randomico = new Lugares(prototipo.getTotal());
		List<Pessoa> pessoas = prototipo.obterPessoas();
		for(Pessoa pessoa : pessoas){
			randomico.set(pessoa, Rand.random.nextInt(randomico.getTotal()));
		}
		return randomico;
	}

	@Override
	protected Lugares cruzar(Lugares pai, Lugares mae, boolean mutacao) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public int pontuar(Lugares individuo) {
		return individuo.pontuar();
	}

}
