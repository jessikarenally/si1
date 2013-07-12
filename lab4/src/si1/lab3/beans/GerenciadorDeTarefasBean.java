package si1.lab3.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import si1.lab3.model.GerenciadorDeTarefas;
import si1.lab3.model.Tarefa;

@ManagedBean(name="gerenciadorBean")
public class GerenciadorDeTarefasBean {
	 static GerenciadorDeTarefas gerenciador;
	//List<Tarefa> completas;
	 static List<Tarefa> incompletas;
	 String nome;
	 String descricao;
	 String data;
	 String horaLimite;
	 //String ordCompletas;
	 //String ordIncompletas;
	 Tarefa selecionada;
	
	public GerenciadorDeTarefasBean() {
		 this.gerenciador = new GerenciadorDeTarefas();
	}
	
	public void addNewTask(){
		try{
			gerenciador.addNovaTarefa(nome, new Date(2015,10,12), "des", "00:00:00");
		}catch(Exception e){
			msg(e.getMessage());
		}
		
		//this.completas = gerenciador.tarefasCompletas();
		this.incompletas = gerenciador.tarefasIncompletas();
		setNome("");
		setDescricao("");
		setData("");
		//System.out.println(completas.size());
		System.out.println(incompletas.size());
		System.out.println(gerenciador.getTarefas().size());

	}
	public void iai(){
		gerenciador.addNovaTarefa("agora presta ne?", new Date(2015,10,12), "des", "00:00:00");
		this.incompletas = gerenciador.tarefasIncompletas();
		System.out.println(incompletas.size());
		System.out.println(gerenciador.getTarefas().size());
	}
	
	public void editTask(){
		gerenciador.editTask(this.selecionada,nome, data, descricao, horaLimite);
	}
	/**	
	public Tarefa getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Tarefa selecionada) {
		this.selecionada = selecionada;
	}

	public void ordenaC(){
		if(ordCompletas.equals(1)){
			
		}else{
			ordenaDecrescente();
		}
	}
	
	public void ordenaDecrescente() {
		
	}

	public void ordenaI(){
		if(ordIncompletas.equals(1)){
			
		}else{
			
		}
	}
	
	
	public String getOrdIncompletas() {
		return ordIncompletas;
	}

	public void setOrdIncompletas(String ordIncompletas) {
		this.ordIncompletas = ordIncompletas;
	}

	public String getOrdCompletas() {
		return ordCompletas;
	}
	public void setOrdCompletas(String ordCompletas) {
		this.ordCompletas = ordCompletas;
	}*/

	public void msg(String text){
		FacesContext context = FacesContext.getCurrentInstance();  
		
		context.addMessage(null, new FacesMessage(text));  
	}
	
	public void ordenaCrescente(List<Tarefa> list){
		
	}

	public String pageNewTask(){
		return "nova_tarefa?faces-redirect=true";
	}
	
	public String index(){
		return "index?faces-redirect=true";
	}

	public String editar(){
		return "nova_tarefa?faces-redirect=true";
	}
	
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	//public List<Tarefa> getCompletas() {
		//return completas;
	//}

	public void setCompletas(List<Tarefa> completas) {
		//this.completas = completas;
	}

	public List<Tarefa> getIncompletas() {
		return incompletas;
	}

	public void setIncompletas(List<Tarefa> incompletas) {
		this.incompletas = incompletas;
	}

	public String getHoraLimite() {
		return horaLimite;
	}

	public void setHoraLimite(String horaLimite) {
		this.horaLimite = horaLimite;
	}
	
	

}
