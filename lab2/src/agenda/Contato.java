package agenda;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Contato {

	private String nome;
	private List<Numero> numeros;
	private List<String> email;
	private int idade;
	private String descricao;
	private int qntNumero;
	
	public Contato(String nome, List<Numero> numeros){
		if(nome.equals("")) throw new IllegalArgumentException("Por favor, digite seu nome");
		this.nome = nome;
		this.numeros = numeros;
		this.qntNumero = numeros.size();
		email = new ArrayList<String>();
	}
	
	
	public String getDescricao() {
		return descricao;
	}


	public Contato(String nome, Numero numero) {
		List<Numero> num = new ArrayList<Numero>();
		num.add(numero);	
		
		if(nome.equals("")) throw new IllegalArgumentException("Por favor, digite seu nome");
		this.nome = nome;
		this.numeros = numeros;
		this.qntNumero = numeros.size();
		email = new ArrayList<String>();
	}

	public List<String> getEmail() {
		return email;
	}

	public void setEmail(String email) {
		for (String mail : email.split(",")) {
			if(!mail.equals("")) this.email.add(mail);
		}
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public List<Numero> getNumeros() {
		return numeros;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getNome() {					
		return nome;
	}

	public long getQuantTelefones() {
		return numeros.size();
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
		
	}


	public int getQntNumero() {
		return qntNumero;
	}


	public void setQntNumero(int qntNumero) {
		this.qntNumero = qntNumero;
	}

}
