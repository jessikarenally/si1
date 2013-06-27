package agenda;


import java.util.ArrayList;
import java.util.List;

public class AgendaBean {
	
	private List<Contato> contatos;
	
	public AgendaBean(){
		this.contatos = new ArrayList<Contato>(); 
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public long getQuantNumeros(String nome) {
		
		for (Contato contato : contatos) {
			if(contato.getNome().equals(nome)) return contato.getQuantTelefones();
		}
		
		return 0;
	}

	
	public Contato searchContato(String nome) {
		
		for (Contato contato : contatos) {
			if(contato.getNome().equals(nome)) return contato;
		}
		
		return null;
	}
	
	public void addContato(String string, List<Numero> numeros) throws Exception {
		contatos.add(new Contato(string, numeros));
		
	}

	public boolean hasContato(String nome) {
		for (Contato contato : contatos) {
			if(contato.getNome().equals(nome)) return true;
		}
		
		return false;
	}

	public void setCamposOpcionais(String nome, String email, int idade,
			String descricao) {
		
		Contato c1 = searchContato(nome);
		c1.setEmail(email);
		c1.setIdade(idade);
		c1.setDescricao(descricao);
	}

	public List<Contato> searchContatos(String nome) {
		List<Contato> results = new ArrayList<Contato>();
		
		for (Contato contato : contatos) {
			if(contato.getNome().startsWith(nome)) results.add(contato);
		}
		return results;
	}

	
	
	

}
