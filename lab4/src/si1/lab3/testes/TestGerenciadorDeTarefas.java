package si1.lab3.testes;


import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import junit.framework.Assert;
import si1.lab3.model.GerenciadorDeTarefas;

public class TestGerenciadorDeTarefas {
	private GerenciadorDeTarefas g1;
	

	public void testaCriaNovaTarefa(){
		 g1 = new GerenciadorDeTarefas();
		
		try{
		g1.addNovaTarefa("lab3", new Date(2013,11,14), "", "");
		} catch(Exception e){
			Assert.assertEquals("Voce nao pode criar uma tarefa com data anterior a atual", e.getMessage());
		}
		Assert.assertEquals(1, g1.qntdDeTarefas());
		
		try{
		g1.addNovaTarefa("", new Date(2013,11,14), "", "");
		}catch (IllegalArgumentException e){
			
		}
		Assert.assertEquals(1, g1.qntdDeTarefas());
		
		try{
			g1.addNovaTarefa("ano novo, ops!", new Date(2012,10,14), "", "");
		}catch (IllegalArgumentException e){				
		}
		Assert.assertEquals(1, g1.qntdDeTarefas());
			
		
		try{
			g1.addNovaTarefa("ano novo, ops!", new Date(2015,10,14), "", "");
		}catch (IllegalArgumentException e){
				
		}
		Assert.assertEquals(2, g1.qntdDeTarefas());
		
		try{
			g1.addNovaTarefa("ano novo, ops!", new Date(2015,10,14), "", "");
		}catch (IllegalArgumentException e){
				
		}
		Assert.assertEquals(3, g1.qntdDeTarefas());
				
	}

	@SuppressWarnings("deprecation")
	@Test
	public void manipulaStatusDeTarefas() {
		g1 = new GerenciadorDeTarefas();
		g1.addNovaTarefa("entregarLab", new Date(2013,7,11), "aprendendo mais sobre jsf", "23:59:00");
		
		Assert.assertEquals("entregarLab", g1.tarefasIncompletas().get(0).getNome());
		Assert.assertEquals(0, g1.tarefasCompletas().size());
		
		g1.setTarefaComoCompleta("entregarLab");
		
		Assert.assertEquals("entregarLab", g1.tarefasCompletas().get(0).getNome());
		Assert.assertEquals(0, g1.tarefasIncompletas().size());
		
		
		g1.addNovaTarefa("entregarLabs", new Date(2013,7,11), "aprendendo mais sobre jsf e ad1", "23:59:00");
		
		Assert.assertEquals("entregarLabs", g1.tarefasIncompletas().get(0).getNome());
		Assert.assertEquals(1, g1.tarefasCompletas().size());
		
		g1.setTarefaComoCompleta("entregarLabs");
		
		Assert.assertEquals("entregarLabs", g1.tarefasCompletas().get(1).getNome());
		Assert.assertEquals(0, g1.tarefasIncompletas().size());
		Assert.assertEquals(2, g1.getTarefas().size());
		
		g1.addNovaTarefa("entregar", new Date(2013,7,11), "aprendendo mais sobre jsf e ad1", "23:59:00");
		g1.addNovaTarefa("entrega", new Date(2013,7,11), "aprendendo mais sobre jsf e ad1", "23:59:00");
		
		Assert.assertEquals(2, g1.tarefasCompletas().size());
		Assert.assertEquals(2, g1.tarefasIncompletas().size());
		Assert.assertEquals(4, g1.getTarefas().size());
		
		g1.setTarefaComoCompleta("entrega");
		Assert.assertEquals(3, g1.tarefasCompletas().size());
		Assert.assertEquals(1, g1.tarefasIncompletas().size());
		Assert.assertEquals(4, g1.getTarefas().size());
	}

}
