package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import agenda.AgendaBean;
import agenda.Contato;
import agenda.Numero;


@ManagedBean(name = "controller")
@SessionScoped
public class ControllerBean {
	AgendaBean agenda = new AgendaBean();
	List<Contato> contatos = agenda.getContatos();

	String nome;
	String numero;
	String operadora;
	String idade;
	String email;
	String descricao;
	String status;
	List<Contato> resultado;
	
	public void busca(){
		resultado = agenda.searchContatos(nome);
	}
	
	public String addContato() {		
		Numero nmero;		
		try{
			 nmero = new Numero(numero, operadora);
		}catch(Exception e){return index();}
		
		if(agenda.searchContato(nome)!= null){return index();}
		
		List<Numero> numeros = new ArrayList<Numero>();
		numeros.add(nmero);
		try {
			agenda.addContato(nome, numeros);
			agenda.setCamposOpcionais(nome, email, Integer.parseInt(idade), descricao);
		} catch (Exception e) {return index();}
				
		return index();
		
		
	}

	public String cadastro(){
		return "cadastro?faces-redirect=true";
	}

	public String index(){
		return "index?faces-redirect=true";
	}

	public boolean hasContato(String nome) {
		return agenda.hasContato(nome);
	}
	
	public String[] getNomesContatos(){		
		int tamanho = agenda.getContatos().size();
		
		String[] nomes = new String[tamanho];
		int i = 0;
		
		for (Contato contato : agenda.getContatos()) {
			nomes[i] = contato.getNome();
			i++;
		}
		
		return nomes;		
	}
	
	public Long[] getQuanTelefones(){		
		int tamanho = agenda.getContatos().size();		
		
		Long[] qntNumeros = new Long[tamanho];
		int i = 0;
		
		for (Contato contato : agenda.getContatos()) {
			qntNumeros[i] = contato.getQuantTelefones();
			i++;
		}
		
		return qntNumeros;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getOperadora() {
		return operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}
	
	public String getIdade() {
		return idade;
	}
	
	public List<Contato> getContatos() {
		return contatos;
	}
	
	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public List<Contato> getResultado() {
		return resultado;
	}

	public void setResultado(List<Contato> resultado) {
		this.resultado = resultado;
	}
	

}
