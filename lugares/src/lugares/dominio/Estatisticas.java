package lugares.dominio;

public class Estatisticas {

	private int totalSatisfacao;
	private int menorSatisfacao = Pessoa.MAIOR_SATISFACAO;

	
	public void computar(int satisfacao){
		totalSatisfacao += satisfacao;
		menorSatisfacao = Math.min(menorSatisfacao, satisfacao);
	}
	
	public int getTotalSatisfacao() {
		return totalSatisfacao;
	}
	
	public int getMenorSatisfacao() {
		return menorSatisfacao;
	}
	
}
