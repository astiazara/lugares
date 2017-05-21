package lugares.texto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import lugares.dominio.Lugares;
import lugares.dominio.Pessoa;

public class ConversorTextoTest {

	@Test
	public void test() {
		StringBuilder texto = new StringBuilder();
		texto.append("Alexandre	100%	0%	100%	0%	100%	100%	0%	0%	0%	0%	0%	0%	0%	0%	0%	0%\n");
		texto.append("Murphy	100%	0%	75%	0%	25%	25%	25%	25%	0%	25%	0%	25%	0%	75%	0%	100%\n");
		texto.append("Cassiano	75%	0%	100%	0%	25%	25%	50%	25%	25%	0%	75%	0%	25%	0%	100%	0%\n");
		texto.append("Cleber	50%	75%	50%	25%	75%	75%	100%	100%	100%	100%	75%	75%	50%	0%	25%	0%\n");
		texto.append("Fabio	25%	50%	25%	50%	25%	25%	100%	75%	75%	75%	50%	50%	50%	25%	50%	25%\n");
		texto.append("Flavio 0%	0%	0%	0%	0%	0%	0%	0%	0%	0%	0%	0%	0%	0%	0%	0%\n");
		texto.append("Chicao	100%	0%	100%	0%	25%	50%	100%	75%	0%	0%	50%	0%	0%	0%	25%	0%\n");
		texto.append("JoseLuiz	25%	0%	25%	0%	0%	0%	25%	100%	0%	0%	0%	0%	0%	0%	0%	0%\n");
		texto.append("Juliana	100%	0%	100%	0%	100%	100%	50%	0%	50%	0%	50%	0%	50%	0%	50%	0%\n");
		texto.append("Katia	100%	0%	0%	0%	0%	0%	0%	0%	0%	0%	100%	0%	0%	0%	0%	0%\n");
		texto.append("Leonardo	100%	0%	100%	0%	100%	100%	0%	25%	25%	25%	25%	25%	50%	0%	25%	0%\n");
		texto.append("Liliam	25%	0%	100%	0%	0%	0%	50%	75%	25%	50%	75%	100%	25%	25%	0%	50%\n");
		texto.append("Mauricio	75%	50%	75%	25%	100%	75%	0%	0%	0%	0%	50%	50%	0%	0%	25%	25%\n");
		texto.append("Pablo	100%	50%	100%	0%	0%	50%	50%	75%	0%	0%	0%	0%	0%	0%	0%	0%\n");
		texto.append("Rosane	100%	0%	50%	0%	100%	100%	75%	75%	0%	0%	0%	0%	0%	0%	0%	0%\n");
		texto.append("Vitor	0%	0%	0%	0%	0%	0%	0%	0%	0%	0%	0%	0%	0%	0%	0%	0%");
		
		ConversorTexto textoPlanilha = new ConversorTexto();
		Lugares lugares = textoPlanilha.converter(texto.toString());
		
		assertEquals(16, lugares.getTotal());
		
		Pessoa pessoa;
		pessoa = lugares.get(0);
		assertEquals("Alexandre", pessoa.getNome());
	}

}
