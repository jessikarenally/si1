package agenda;


import java.util.ArrayList;
import java.util.List;

public class AgendaBean {
	
	private List<Contato> contatos;
	
	public AgendaBean(){
		this.contatos = new ArrayList<Contato>(); 
	}

	public long getQuantNumeros(String nome) {
		
		for (Contato contato : contatos) {
			if(contato.getNome().equals(nome)) return contato.getQuantTelefones();
		}
		
		return 0;
	}

	public void addContato(String string, List<Numero> numeros) {
		contatos.add(new Contato(string, numeros));
		
	}

	public boolean hasContato(String nome) {
		for (Contato contato : contatos) {
			if(contato.getNome().equals(nome)) return true;
		}
		
		return false;
	}
	
	

}
