package lugares.dominio;

public class Pessoa {

	public static final int MENOR_SATISFACAO = 0;
	public static final int MAIOR_SATISFACAO = 100;
	
	private String nome;
	private Lugares lugares;
	private int[] satisfacao;
	
	
	public Pessoa(Lugares lugares){
		this.lugares = lugares;
		satisfacao = new int[lugares.getTotal()];
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setSatisfacao(int indiceLugar, int satisfacaoLugar){
		lugares.validarIndiceLugar(indiceLugar);
		validarSatisfacao(satisfacaoLugar);
		
		satisfacao[indiceLugar] = satisfacaoLugar;
	}
	public int getSatisfacao(int indiceLugar){
		lugares.validarIndiceLugar(indiceLugar);
		
		return satisfacao[indiceLugar];
	}
	
	private void validarSatisfacao(int satisfacao){
		if(satisfacao < MENOR_SATISFACAO || satisfacao > MAIOR_SATISFACAO){
			throw new IllegalArgumentException("Satisfação fora do intervalo permitido.");
		}
	}
	
}
