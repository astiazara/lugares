package lugares.texto;

import java.util.Scanner;

import lugares.dominio.Lugares;
import lugares.dominio.Pessoa;

public class ConversorTexto {
	
	private Lugares lugares;

	
	public Lugares converter(String texto){
		lugares = null;
		
		Scanner scanner = new Scanner(texto);
		scanner.useDelimiter("\\n");
	
		while (scanner.hasNext()) {
			processar(scanner.next());
		}
		scanner.close();

		return lugares;
	}

	private void processar(String linhaTexto) {
		Linha linha = new Linha(linhaTexto);
		validarMinimoColunas(linha);
		criarLugaresSeNecessario(linha);
		validarTotalColunasComLugares(linha);
		criarPessoa(linha);
	}

	private void criarPessoa(Linha linha) {
		Pessoa pessoa = new Pessoa(lugares);
		pessoa.setNome(linha.get(0));
		for (int i = 1; i < linha.getTamanho(); i++) {
			pessoa.setSatisfacao(i-1, criarSatisfacao(linha.get(i)));
		}
		lugares.sentarOndeVago(pessoa);
	}

	private int criarSatisfacao(String texto) {
		texto = texto.replace("%", "");
		return Integer.parseInt(texto);
	}

	private void validarTotalColunasComLugares(Linha linha) {
		int numeroColunas = lugares.getTotal() + 1;
		if(linha.getTamanho() != numeroColunas){
			throw new IllegalArgumentException("Linha deve ter " + numeroColunas + " colunas.");
		}
	}

	private void validarMinimoColunas(Linha linha) {
		if(linha.getTamanho() < 3){
			throw new IllegalArgumentException("Linha deve ter no minimo 3 colunas.");
		}
	}

	private void criarLugaresSeNecessario(Linha linha) {
		if(lugares != null){
			return;
		}
		
		int total = linha.getTamanho() - 1;
		lugares = new Lugares(total);
	}
	
}
