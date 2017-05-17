package dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class PessoaTest {

	@Test
	public void pontuar_0pqSemPreferencia() {
		Lugares lugares = new Lugares(5);
		Pessoa pessoa = new Pessoa(lugares);
		
		assertEquals(0, pessoa.pontuar(0));
		assertEquals(0, pessoa.pontuar(1));
		assertEquals(0, pessoa.pontuar(2));
		assertEquals(0, pessoa.pontuar(3));
		assertEquals(0, pessoa.pontuar(4));
	}

	@Test(expected=IllegalArgumentException.class)
	public void pontuar_lugarInvalidoPqNegativo() {
		Lugares lugares = new Lugares(5);
		Pessoa pessoa = new Pessoa(lugares);
		
		pessoa.pontuar(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void pontuar_lugarInvalidoPqTotal() {
		Lugares lugares = new Lugares(5);
		Pessoa pessoa = new Pessoa(lugares);
		
		pessoa.pontuar(5);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void pontuar_lugarInvalidoPqMaiorTotal() {
		Lugares lugares = new Lugares(5);
		Pessoa pessoa = new Pessoa(lugares);
		
		pessoa.pontuar(6);
	}

	@Test
	public void pontuar_5pqSentouPrimeiroDesejado() {
		Lugares lugares = new Lugares(5);
		Pessoa pessoa = new Pessoa(lugares);
		pessoa.adicionarLugarDesejado(0);
		
		assertEquals(5, pessoa.pontuar(0));
	}
	
	@Test
	public void pontuar_4pqSentouSegundoDesejado() {
		Lugares lugares = new Lugares(5);
		Pessoa pessoa = new Pessoa(lugares);
		pessoa.adicionarLugarDesejado(0);
		pessoa.adicionarLugarDesejado(1);
		
		assertEquals(4, pessoa.pontuar(1));
	}
	
	@Test
	public void pontuar_0pqNaoSentouDesejadoSemIndesejados() {
		Lugares lugares = new Lugares(5);
		Pessoa pessoa = new Pessoa(lugares);
		pessoa.adicionarLugarDesejado(0);
		
		assertEquals(0, pessoa.pontuar(1));
	}
	
	@Test
	public void pontuar_0pqNaoSentouDesejadoNemIndesejados() {
		Lugares lugares = new Lugares(5);
		Pessoa pessoa = new Pessoa(lugares);
		pessoa.adicionarLugarDesejado(0);
		pessoa.adicionarLugarIndesejado(4);
		
		assertEquals(0, pessoa.pontuar(1));
	}
	
	@Test
	public void pontuar_negativo5pqSentouPrimeiroIndesejado() {
		Lugares lugares = new Lugares(5);
		Pessoa pessoa = new Pessoa(lugares);
		pessoa.adicionarLugarIndesejado(4);
		
		assertEquals(-5, pessoa.pontuar(4));
	}
	
	@Test
	public void pontuar_negativo4pqSentouSegundoIndesejado() {
		Lugares lugares = new Lugares(5);
		Pessoa pessoa = new Pessoa(lugares);
		pessoa.adicionarLugarIndesejado(4);
		pessoa.adicionarLugarIndesejado(3);
		
		assertEquals(-4, pessoa.pontuar(3));
	}
}
