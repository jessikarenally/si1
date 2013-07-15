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
		if(exist(nome)) throw new IllegalArgumentException("Já existe uma tarefa com esse nome");
		tarefas.add(new Tarefa(nome, data, descricao, horaLimite));
	}

	private boolean exist(String nome) {
		for(Tarefa t: tarefas){
			if(t.getNome().equals(nome)) return true;
		}
		return false;
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
			String descricao, String horaLimite) throws IllegalArgumentException{
		boolean status = selecionada.getTarefaCompleta();
		
		if(exist(selecionada.getNome())){
			for(int i = 0; i < tarefas.size(); i++){
				if(this.tarefas.get(i).getNome().equals(selecionada.getNome())) {
					edit(nome, data, descricao, horaLimite, status, i);
					break;
				}
			}
		}
		
		
	}

	private void edit(String nome, String data, String descricao,
			String horaLimite, boolean status, int i) {
		
		Tarefa editada = criaTarefa(nome, data, descricao, horaLimite);
		if(editada != null && nomeValido(editada, i)) {
			editada.setTarefaCompleta(status);
			tarefas.set(i, editada);
		}
	}
	
	private boolean nomeValido(Tarefa editada, int i) {
		if(!exist(editada.getNome())) return true;
		else{
			return tarefas.get(i).getNome().equals(editada.getNome());
		}
	}

	private Tarefa criaTarefa(String nome, String data, String descricao,
			String horaLimite) throws IllegalArgumentException{
		Date d;
		try{
			d = criaData(data);
		}catch(Exception e){
			throw new IllegalArgumentException("A data nao esta em um formato valido");
		}
		Tarefa editada = new Tarefa(nome, d, descricao, horaLimite);
		return editada;
	}
	
	private Date criaData(String data) throws Exception{
		Date date;
		if(data != "") {
			int[] d1 = {Integer.parseInt(data.split("/")[0]), Integer.parseInt(data.split("/")[1]), Integer.parseInt(data.split("/")[2])};
			date = new Date(d1[2]-1900,d1[1]-1,d1[0]);
		}else date = null;
		return date;
	}

	public void remove(String nome) {
		for(int i = 0; i < tarefas.size(); i++){
			if(tarefas.get(i).getNome().equals(nome)){
				tarefas.remove(i);
				break;
			}
		}
		
	}

}
