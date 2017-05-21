package lugares.dominio;

import java.util.ArrayList;
import java.util.List;

public class Lugares {

	private Pessoa[] lugares;
	
	
	public Lugares(int total){
		if(total < 2){
			throw new IllegalArgumentException("Total deve ser no minimo 2.");
		}
		lugares = new Pessoa[total];
	}
	
	public void set(Pessoa pessoa, int indiceLugar){
		validarIndiceLugar(indiceLugar);
		if(contem(pessoa)){
			throw new IllegalArgumentException("Pessoa jah atribuida");
		}
		lugares[indiceLugar] = pessoa;
	}
	public Pessoa get(int indiceLugar){
		validarIndiceLugar(indiceLugar);
		return lugares[indiceLugar];
	}
	
	public void remover(int indiceLugar){
		validarIndiceLugar(indiceLugar);
		lugares[indiceLugar] = null;
	}

	void validarIndiceLugar(int indiceLugar) {
		if(indiceLugar < 0 || indiceLugar >= lugares.length){
			throw new IllegalArgumentException("Lugar inexistente: " + indiceLugar);
		}
	}

	public int getTotal() {
		return lugares.length;
	}
	
	public Estatisticas calcularEstatisticas(){
		Estatisticas estatisticas = new Estatisticas();
		
		for (int i = 0; i < lugares.length; i++) {
			Pessoa pessoa = lugares[i];
			if(pessoa != null){
				estatisticas.computar(pessoa.getSatisfacao(i));
			}
		}
		return estatisticas;
	}

	public List<Pessoa> obterPessoas() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		for(Pessoa pessoa : lugares){
			if(pessoa != null){
				pessoas.add(pessoa);
			}
		}
		return pessoas;
	}
	
	public int indiceDe(Pessoa pessoa){
		if(pessoa == null){
			throw new IllegalArgumentException("Pessoa nao pode ser nula");
		}
		
		for (int i = 0; i < lugares.length; i++) {
			if(lugares[i] == pessoa){
				return i;
			}
		}
		return -1;
	}
	
	public boolean contem(Pessoa pessoa){
		return indiceDe(pessoa) != -1;
	}

	public boolean ehVago(int i) {
		return get(i) == null;
	}
	
}
