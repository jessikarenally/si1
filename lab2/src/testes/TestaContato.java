package testes;

import org.junit.Assert;
import org.junit.Test;

import agenda.Contato;
import agenda.Numero;

public class TestaContato {
	Contato c1;
	
	@Test
	public void testaConstrutor(){
		try{
			c1 = new Contato("", new Numero("44444444", "oi"));
		}catch(Exception e){
			Assert.assertEquals("Por favor, digite seu nome", e.getMessage());
		}
		
	}

}
