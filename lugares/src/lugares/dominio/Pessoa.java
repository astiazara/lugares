package lugares.dominio;

import java.util.ArrayList;
import java.util.List;

public class Pessoa {

	private String nome;
	private Lugares lugares;
	private List<Integer> lugaresDesejados = new ArrayList<>(2);
	private List<Integer> lugaresIndesejados = new ArrayList<>(5);
	
	public Pessoa(Lugares lugares){
		this.lugares = lugares;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void adicionarLugarDesejado(Integer indiceLugar){
		lugares.validarIndiceLugar(indiceLugar);
		
		if(!lugaresDesejados.contains(indiceLugar)){
			lugaresDesejados.add(indiceLugar);
			lugaresIndesejados.remove(indiceLugar);
		}
	}
	
	public void adicionarLugarIndesejado(Integer indiceLugar){
		lugares.validarIndiceLugar(indiceLugar);
		
		if(!lugaresIndesejados.contains(indiceLugar)){
			lugaresIndesejados.add(indiceLugar);
			lugaresDesejados.remove(indiceLugar);
		}
	}

	public int pontuar(Integer indiceLugar){
		lugares.validarIndiceLugar(indiceLugar);
		
		if(lugaresDesejados.contains(indiceLugar)){
			return lugares.getTotal() - lugaresDesejados.indexOf(indiceLugar);
		} else if(lugaresIndesejados.contains(indiceLugar)){
			return lugaresIndesejados.indexOf(indiceLugar) - lugares.getTotal();
		} else {
			return 0;
		}
	}
	
}
