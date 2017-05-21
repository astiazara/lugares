package lugares.texto;

import java.util.ArrayList;
import java.util.List;

public class Linha {
private List<String> palavras;
	
	public Linha(String texto){
		palavras = new ArrayList<String>();
		
		if(texto == null || texto.length() == 0)
			return;
		
		// Caracteres ignorados.
		texto = texto.replaceAll("\r", "");
		
		// Caracteres separadores.
		texto = texto.replaceAll("\t", " ");
		
		String[] textos = texto.split(" ");
		for (int i = 0; i < textos.length; i++) {
			if(textos[i] != null && textos[i].length() > 0){
				palavras.add(textos[i]);
			}
		}
	}
	
	public int getTamanho(){
		return palavras.size();
	}

	public boolean ehIgual(Linha outra) {
		if(this.getTamanho() != outra.getTamanho())
			return false;
		
		for (int i = 0; i < this.getTamanho(); i++) {
			if(!palavras.get(i).equalsIgnoreCase(outra.palavras.get(i))){
				return false;
			}
		}
		
		return true;
	}

	public String get(int indice) {
		return palavras.get(indice);
	}
	
}
