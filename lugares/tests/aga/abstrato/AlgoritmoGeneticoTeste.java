package aga.abstrato;

import java.util.ArrayList;
import java.util.List;

import aga.Rand;

public class AlgoritmoGeneticoTeste extends AlgoritmoGeneticoAbstrato<String> {

	private List<String> repertorio;
	
	public AlgoritmoGeneticoTeste(){
		repertorio = new ArrayList<String>(5);
		repertorio.add("xxxx");
		repertorio.add("axxx");
		repertorio.add("xmxx");
		repertorio.add("xxox");
		repertorio.add("xxxr");		
	}
	
	@Override
	protected String criarIndividuoRandomico() {
		return repertorio.get(Rand.random.nextInt(repertorio.size()));
	}

	@Override
	protected String cruzar(String pai, String mae, boolean mutacao) {
		char[] genesPai = pai.toCharArray();
		char[] genesMae = mae.toCharArray();
		char[] genesFilho = new char[4];
		for(int i=0; i < 4; i++){
			if(Rand.random.nextBoolean()){
				genesFilho[i] = genesPai[i];
			} else {
				genesFilho[i] = genesMae[i];
			}
		}
		return new String(genesFilho);
	}

	@Override
	public int pontuar(String individuo) {
		char[] esperado = "amor".toCharArray();
		char[] gerado = individuo.toCharArray();
		
		int pontos = 0;
		for (int i = 0; i < esperado.length; i++) {
			if(gerado[i] == esperado[i]){
				pontos++;
			}
		}
		return pontos;
	}

}
