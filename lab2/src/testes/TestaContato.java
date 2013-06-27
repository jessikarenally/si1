package testes;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import agenda.Contato;
import agenda.Numero;

public class TestaContato {
	Contato c1, c2;
	
	@Test
	public void testaConstrutor(){
		try{
			c1 = new Contato("", new Numero("44444444", "oi"));
			Assert.fail();
		}catch(Exception e){
			Assert.assertEquals("Por favor, digite seu nome", e.getMessage());
		}
		
		try{
			List<Numero> num = new ArrayList<Numero>();
			num.add(new Numero("1234567890"));
			c2 = new Contato("Joana", num);
		}catch(Exception e){
			//Assert.fail();
			Assert.assertEquals("Por favor, digite seu nome", e.getMessage());
		}
		
		
		
		
		
		
		
	}

}
