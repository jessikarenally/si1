package testes;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.internal.runners.statements.Fail;

import agenda.AgendaBean;
import agenda.Numero;
public class TestaAgenda {

	@Test
	public void testaContatoParametrosMinimos(){
		AgendaBean agenda1 = new AgendaBean();
		
		Assert.assertEquals(0, agenda1.getQuantNumeros("Joao"));
		List<Numero> numeros = new ArrayList<Numero>();
		numeros.add(new Numero(311288675432L));
		
		try{
			agenda1.addContato("Joao", numeros);
		}catch(IllegalArgumentException e){
			
		}
		
		Assert.assertEquals(1, agenda1.getQuantNumeros("Joao"));

		List<Numero> numeros2 = new ArrayList<Numero>();
		numeros2.add(new Numero(311288675455L));
		Assert.assertEquals(0, agenda1.getQuantNumeros("Joana"));
		
		try{
			agenda1.addContato("Joana", numeros2);
		}catch(IllegalArgumentException e){
			
		}
		
		Assert.assertEquals(1, agenda1.getQuantNumeros("Joana"));
		
	}
	@Test
	public void testaSemNome(){
		AgendaBean agenda2 = new AgendaBean();
		
		List<Numero> numeros = new ArrayList<Numero>();
		numeros.add(new Numero(311288675432L));
		
		try{
			agenda2.addContato("", numeros);
			fail();
		}catch(IllegalArgumentException e){
			System.out.println(e);			
		}
		
		
	}
	
	
	
}
