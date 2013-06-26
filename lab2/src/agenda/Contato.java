package agenda;

import java.util.List;

public class Contato {

	private String nome;
	private List<Numero> numeros;
	private String email;
	private int idade;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Contato(String nome, List<Numero> numeros){
		if(nome.equals("")) throw new IllegalArgumentException("Por favor, digite seu nome");
		
		this.nome = nome;
		this.numeros = numeros;
	}

	public String getNome() {					
		return nome;
	}

	public long getQuantTelefones() {
		return numeros.size();
	}

}
