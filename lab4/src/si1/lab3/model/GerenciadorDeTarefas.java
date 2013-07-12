package si1.lab3.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GerenciadorDeTarefas {
	private List<Tarefa> tarefas;

	public GerenciadorDeTarefas() {
		this.tarefas = new ArrayList<Tarefa>();
	}
	
	public void addNovaTarefa(String nome, Date data, String descricao, 
			String horaLimite) throws IllegalArgumentException{
	
		tarefas.add(new Tarefa(nome, data, descricao, horaLimite));
	}

	public int qntdDeTarefas() {
		return tarefas.size();
	}

	public List<Tarefa> tarefasIncompletas() {
		List<Tarefa> incompletas = new ArrayList<Tarefa>();
		for(Tarefa t: tarefas){
			if(!t.getTarefaCompleta()) incompletas.add(t);
		}
		
		return incompletas;
	}

	public List<Tarefa> tarefasCompletas() {
		List<Tarefa> completas = new ArrayList<Tarefa>();
		
		for(Tarefa t: tarefas){
			if(t.getTarefaCompleta()) completas.add(t);
		}
		
		return completas;	
	}

	public void setTarefaComoCompleta(String nome) {
		for(Tarefa t: tarefas){
			if(t.getNome().equals(nome)) t.tarefaCompleta();
		}
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void editTask(Tarefa selecionada, String nome, String data,
			String descricao, String horaLimite) {
		
	}

}
