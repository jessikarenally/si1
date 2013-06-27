package testes;

import org.junit.Test;

import junit.framework.Assert;
import agenda.Numero;

public class TestNumero {
	Numero n1, n2, n3;
	
	@Test
	public void testaConstrutor(){
		try{
			n1 = new Numero("111111111111", "oi");
		} catch (IllegalArgumentException e){
			Assert.assertEquals("Numero fora do padrao", e.getMessage());
		}
		
		
		Assert.assertEquals("111111111111", n1.getNumero());
		Assert.assertEquals("oi", n1.getOperadora());
		
		
		try{
			n2 = new Numero("444");
			Assert.fail();
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Numero fora do padrao", e.getMessage());
		}
		
		try{
			n3 = new Numero("444", "oi");
			Assert.fail();
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Numero fora do padrao", e.getMessage());
		}
		
		try{
			n2 = new Numero("444");
			Assert.fail();
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Numero fora do padrao", e.getMessage());
		}
		
	}

}
