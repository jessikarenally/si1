package si1.lab3.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import si1.lab3.model.GerenciadorDeTarefas;
import si1.lab3.model.Tarefa;
@SessionScoped
@ManagedBean(name="gerenciadorBean")
public class GerenciadorDeTarefasBean {
	 static GerenciadorDeTarefas gerenciador;
	 static List<Tarefa> completas;
	 static List<Tarefa> incompletas;
	 List<String> nomes;
	 List<String> allTasks;
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
	
			/** Actions */

	public void addNewTask(){
		System.out.println("cria"+horaLimite);
		try{
			gerenciador.addNovaTarefa(nome, criaData(), descricao, horaLimite);
		}catch(Exception e){
			msg(e.getMessage());
		}
		
		updateStatusTarefas();
		resetFields();		
	}

	public void marcaComoCompleta() {
		gerenciador.setTarefaComoCompleta(nome);
		updateStatusTarefas();

	}
	
	public void removeTarefa() {
		gerenciador.remove(nome);
		updateStatusTarefas();

	}
	
	public void iai(){
		gerenciador.addNovaTarefa("ja", new Date(113, 7,12), "sem", "23:59:00");
		 gerenciador.addNovaTarefa("j", new Date(113, 7,11), "sem", "23:59:00");
		 gerenciador.addNovaTarefa("jab", new Date(113, 7,12), "sem", "23:59:00");
		 gerenciador.addNovaTarefa("je", null, "sem", "23:59:00");
		 gerenciador.addNovaTarefa("job", new Date(113, 7,12), "sem", "23:59:00");
		 gerenciador.addNovaTarefa("ji", new Date(113, 7,05), "sem", "23:59:00");
	}
	
	public void editTask(){
		System.out.println("tentative2"+this.horaLimite);
		gerenciador.editTask(this.selecionada,nome, data, descricao, horaLimite);
		updateStatusTarefas();
		resetFields();
	}

			/**	Page Redirects	**/
	
	public String pageNewTask(){
		return "nova_tarefa?faces-redirect=true";
	}
	
	public String index(){
		return "index?faces-redirect=true";
	}
	
	public String editar(){
		return "editar?faces-redirect=true";
	}
	
			/** Methods auxiliaries*/

	private Date criaData() {
		Date date;
		if(data != "") {
			int[] d1 = {Integer.parseInt(data.split("/")[0]), Integer.parseInt(data.split("/")[1]), Integer.parseInt(data.split("/")[2])};
			date = new Date(d1[2]-1900,d1[1]-1,d1[0]);
		}else date = null;
		
		return date;
	}
	
	private void updateStatusTarefas(){
		this.completas = gerenciador.tarefasCompletas();
		this.incompletas = gerenciador.tarefasIncompletas();
		this.nomes = new ArrayList<String>();
		this.allTasks = new ArrayList<String>();
		
		for(Tarefa t: incompletas){
			nomes.add(t.getNome());
		}
		
		for(Tarefa t: gerenciador.getTarefas()){
			allTasks.add(t.getNome());
		}
	}
	
	private void resetFields(){
		setNome("");
		setDescricao("");
		setData("");
		setHoraLimite("");
		
	}
	
	public int sortByDate(String data1, String data2){
		int compare = data1.compareTo(data2);
		System.out.println("ta te usando?");
		if(data1.equals("")) {
			System.out.println("primeiro if " + data1 + data2);
			return -1;
		}
		if(data2.equals("")) {
			System.out.println("segundo if " + data1 + data2);
			return 1;
		}
		
		return compare;
	}
	
	public int sortByHour(String hour1, String hour2){
		int compare = hour1.compareTo(hour2);
		if(hour1.equals("")) return -1;
		if(hour2.equals("")) return 1;
		
		return compare;
	}
	
	public String onRowSelect(SelectEvent event) {  
       System.out.println("aqui" +"Tarefa selecionada"+ ((Tarefa) event.getObject()).getNome());
       selecionada = (Tarefa) event.getObject();
       this.nome = this.selecionada.getNome();
       this.data = this.selecionada.getDataFormatada();
       this.descricao = this.selecionada.getDescricao();
       setHoraLimite(this.selecionada.getHoraLimiteFormata());
       System.out.println("horalimite"+ this.horaLimite+"ffff");
       return editar();
       
    }  
	
				/** Getters e Setters */
	
	
	public Tarefa getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Tarefa selecionada) {
		this.selecionada = selecionada;
	}
	/**
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
	
	public String getHoraLimite() {
		return horaLimite;
	}
	
	public void setHoraLimite(String horaLimite) {
		this.horaLimite = horaLimite;
	}

	public List<Tarefa> getCompletas() {
		return completas;
	}

	public void setCompletas(List<Tarefa> completas) {
		this.completas = completas;
	}

	public List<Tarefa> getIncompletas() {
		return incompletas;
	}

	public void setIncompletas(List<Tarefa> incompletas) {
		this.incompletas = incompletas;
	}

	public List<String> getNomes() {
		return nomes;
	}

	public void setNomes(List<String> nomes) {
		this.nomes = nomes;
	}

	public List<String> getAllTasks() {
		return allTasks;
	}

	public void setAllTasks(List<String> allTasks) {
		this.allTasks = allTasks;
	}

	
	

}
