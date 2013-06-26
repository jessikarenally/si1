package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import agenda.AgendaBean;
import agenda.Numero;


@ManagedBean(name = "controller")
@RequestScoped
public class ControllerBean {
	AgendaBean agenda = new AgendaBean();
	
	

	public void redirect(){
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("cadastro.xhtml");
		} catch (IOException e) {
			System.out.println("opa");;
		} 
	}



	public void addContato(String nome, String numero, String op,
			String email, String idade) {
		
		Numero nmero = new Numero(numero, op);
		List<Numero> numeros = new ArrayList<Numero>();
		numeros.add(nmero);
		agenda.addContato(nome, numeros);
		
		
	}

	public String index(){
		return "index2.xhtml"; //"/faces/index2?faces-redirect=true";
	}


	public boolean hasContato(String nome) {
		return agenda.hasContato(nome);
	}
}
