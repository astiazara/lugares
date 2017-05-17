package dominio;

public class Lugares {

	private Pessoa[] lugares;
	
	public Lugares(int total){
		lugares = new Pessoa[total];
	}
	
	public void set(Pessoa pessoa, int indiceLugar){
		validarIndiceLugar(indiceLugar);
		lugares[indiceLugar] = pessoa;
	}

	void validarIndiceLugar(int indiceLugar) {
		if(indiceLugar < 0 || indiceLugar >= lugares.length){
			throw new IllegalArgumentException("Lugar inexistente: " + indiceLugar);
		}
	}

	public int getTotal() {
		return lugares.length;
	}
	
	public int pontuar(){
		int pontos = 0;
		for (int i = 0; i < lugares.length; i++) {
			Pessoa pessoa = lugares[i];
			if(pessoa != null){
				pontos += pessoa.pontuar(i);
			}
		}
		return pontos;
	}
	
}
