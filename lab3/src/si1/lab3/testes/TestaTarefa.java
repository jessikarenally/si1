package si1.lab3.testes;
import java.util.Date;

import javax.naming.TimeLimitExceededException;

import org.junit.*;

import si1.lab3.model.Tarefa;

public class TestaTarefa {
	Tarefa t1, t2, t3, t4, t5;
	
	@SuppressWarnings("deprecation")
	@Test
	public void testaConstrutorOpcoesData(){
		t1 = new Tarefa("Estudar");
		
		Assert.assertEquals("Estudar", t1.getNome());
		Assert.assertEquals(t1.getDescricao(), "");
		Assert.assertEquals("", t1.getDataFormatada());
		
		t1.setDescricao("estudar para todas as disciplinas");
		Assert.assertEquals(t1.getDescricao(), "estudar para todas as disciplinas");
		
		Date d = new Date();
		Assert.assertEquals(t1.getData(), null);		
		try{
			t1.setData(d);
		}catch (TimeLimitExceededException e){
			Assert.fail();
			Assert.assertEquals("Voce nao pode criar uma tarefa com data anterior a atual", e.getMessage());
		}
		Assert.assertEquals(d,t1.getData());		
		Assert.assertEquals("13/07/2013", t1.getDataFormatada());
		
		Date d2 = new Date(113,06,14);
		try {
			t2 = new Tarefa("Missa", d2, "meu tesouro", "00:00:00");
		} catch (Exception e) {
			Assert.fail();
		}
		
		Assert.assertEquals("Missa", t2.getNome());
		Assert.assertEquals("meu tesouro", t2.getDescricao());
		Assert.assertEquals("00:00:00", t2.getHoraLimite());
		Assert.assertEquals(d2, t2.getData());
		Assert.assertEquals("14/07/2013", t2.getDataFormatada());
		
		Date d3 = new Date(105,06,14);
		System.out.println(d3.getYear());
		try{
			t3 = new Tarefa("Missa", d3, "meu tesouro", "00:00:00");
			Assert.fail();
		}catch (IllegalArgumentException e){
			Assert.assertEquals("Voce nao pode criar uma tarefa com data anterior a atual", e.getMessage());
		}
	
		
		try{
			t4 = new Tarefa("lab3", new Date(113,11,20), "", "");
		}catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			Assert.fail();
		}
		
		Assert.assertEquals(new Date(113,11,20), t4.getData());
		Assert.assertEquals("20/12/2013", t4.getDataFormatada());
		
		try{
			t5 = new Tarefa("lab3", new Date(100,11,14), "", "");
			Assert.fail();
		}catch (IllegalArgumentException e) {
			Assert.assertEquals("Voce nao pode criar uma tarefa com data anterior a atual", e.getMessage());
		}
		System.out.println(new Date(1,1,1).toString());
				
	}
	
	@Test
	public void testaConstrutorOpcoesHora() {
		Date d2 = new Date(113,06,14);
		try {
			t2 = new Tarefa("Missa", d2, "meu tesouro", "0000:00");
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("A hora limite n�o � uma hora v�lida", e.getMessage());

		}
		
		try {
			t2 = new Tarefa("Missa", d2, "meu tesouro", "00:74:00");
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("A hora limite n�o � uma hora v�lida", e.getMessage());
		}
		
		try {
			t2 = new Tarefa("Missa", d2, "meu tesouro", "27:45:00");
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("A hora limite n�o � uma hora v�lida", e.getMessage());
		}
		
		try {
			t2 = new Tarefa("Missa", d2, "meu tesouro", "12:14:200");
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("A hora limite n�o � uma hora v�lida", e.getMessage());
		}
		
		try {
			t2 = new Tarefa("Missa", d2, "meu tesouro", "12:14:20");
		} catch (IllegalArgumentException e) {
			Assert.fail();
			Assert.assertEquals("A hora limite n�o � uma hora v�lida", e.getMessage());
		}

	}
	
	
	@Test
	public void mudaStatusTarefa(){
		Date d2 = new Date(113,06,14);
		try {
			t2 = new Tarefa("Missa", d2, "meu tesouro", "12:14:20");
		} catch (IllegalArgumentException e) {
			Assert.fail();
			Assert.assertEquals("A hora limite n�o � uma hora v�lida", e.getMessage());
		}
		
		Assert.assertEquals(false, t2.getTarefaCompleta());
		t2.tarefaCompleta();
		
		Assert.assertEquals(true, t2.getTarefaCompleta());
	}

}
