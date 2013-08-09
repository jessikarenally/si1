package Bean;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import Controller.Controladora;
import Model.Contato;
import Model.Telefone;
import Model.Usuario;

@ManagedBean(name = "agendaBean", eager = true)
@SessionScoped
public class Agenda implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String ARQUIVO = "Agenda.txt";
	private String login;
	private String password;

	private String newLogin;
	private String newPassword;

	public ArrayList<Contato> contatos;
	private Controladora controladora;
	
	
	private String email;
	private Contato contato;
	private Telefone telefone;
	private Contato contatoSelecionado;
	private boolean telefoneInvalido;
	private String contatoValido;
	private String tipoDeBusca;
	private String busca;
	private ArrayList<Contato> resultadoBusca;

	public Agenda() {
		try {
			lerDados();
		} catch (IOException e) { }
		
		initBean();
	}
	
	private void initBean() {		
		this.login = "";
		this.password = "";
		this.newLogin = "";
		this.newPassword = "";

		this.email = "";
		this.contatoValido = "false";
		this.telefoneInvalido = false;
		this.contato = new Contato();
		this.telefone = new Telefone();
		this.contatoSelecionado = new Contato();
		this.busca = "";
		this.tipoDeBusca = "1";
		this.resultadoBusca = new ArrayList<Contato>();
	}

	public String saveButton() {
		Usuario novoUsuario = new Usuario(newLogin, newPassword);
		if(this.controladora.addUsuario(novoUsuario)){
			msgUsuario("Usuário Cadastrado", "Seja Bem-vindo " + newLogin);
			initBean();
			this.contatos = novoUsuario.getContatos();
			try {
				persistirDados();
			}catch (Exception e) {}
			
			return "index.seam";
		} else{
			msgUsuario("Usuário já existente", "Escolha outro login");
			return "";
		}		
	}

	public String loginButton() {
		ArrayList<Contato> contatos = this.controladora.buscaUsuario(login, password);
		if(contatos != null){
			this.contatos = contatos;
			msgUsuario("Usuário logado", "Seja Bem-vindo " + login);
			return "main.seam";
		} else{
			msgUsuario("Login não realizado", "Senha errada ou Login inexistente");
			return "";	
		}
		
	}

	public String logoffButton() {
		initBean();
		return "index.seam";
	}

	private void msgUsuario(String string1, String string2) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(string1, string2));
	}


	public void addTelefone(ActionEvent event) {
		this.telefoneInvalido = false;
		try {
			this.contato.addTelefone(telefone);
			persistirDados();
		} catch (IllegalArgumentException e) {
			this.telefoneInvalido = true;
		} catch (IOException e) {
		}
		this.telefone = new Telefone();
	}

	

	public void removeEmail(String email) {
		this.contato.removeEmail(email);
		try {
			persistirDados();
		} catch (Exception e) {
		}
	}

	public void cancelarContato(ActionEvent event) {
		this.telefoneInvalido = false;
		this.contato = new Contato();
		try {
			persistirDados();
		} catch (Exception e) {
		}

	}

	public void addEmail() {
		this.contato.addEmail(this.email);
		this.email = "";
		try {
			persistirDados();
		} catch (Exception e) {
		}
	}

	

	public void addContato(ActionEvent event) throws IllegalArgumentException {
		
		if (!contatos.contains(contato)) {
			contatos.add(contato);
		}
		contato = new Contato();
		this.telefone = new Telefone();
		try {
			this.persistirDados();
		} catch (Exception e) {
		}
	}

	public void removeTelefone(Telefone tel) {
		contato.removeTelefone(tel);
		try {
			persistirDados();
		} catch (Exception e) {
		}

	}

	
	public void fazBusca(AjaxBehaviorEvent event) {
		resultadoBusca = new ArrayList<Contato>();
		
		for (Contato contato : contatos) {
			boolean parametroValidos = verficaValidade(contato);

			if (tipoDeBusca.equals("1")) {
				buscaPorNomeTelefone(contato);
			} else 	if (tipoDeBusca.equals("2") && parametroValidos) {
				buscaPorIdadeMenor(contato);
			}else if (tipoDeBusca.equals("3")) {
				buscaPorIdadeIgual(contato, parametroValidos);
			}else if (tipoDeBusca.equals("4") && parametroValidos) {
				buscaPorIdadeMaior(contato);
			}

		}
	}

	private boolean verficaValidade(Contato contato) {
		return !(contato.getIdade().equals("") || this.busca //acho q para ser invalido os dois teriam q ser vazios
				.equals(""));
	}

	private void buscaPorIdadeMaior(Contato contato) {
		boolean maior = new Integer(contato.getIdade()) > new Integer(
				this.busca);
		if (maior) {
			resultadoBusca.add(contato);
		}
	}

	private void buscaPorIdadeIgual(Contato contato, boolean parametroValidos) {
		if (contato.getIdade().equals(this.busca) && parametroValidos) {
			resultadoBusca.add(contato);
		}
	}

	private void buscaPorIdadeMenor(Contato contato) {
		boolean menor = new Integer(contato.getIdade()) < new Integer(
				this.busca);
		if (menor) {
			resultadoBusca.add(contato);
		}
	}

	private void buscaPorNomeTelefone(Contato contato) {
		boolean subString = contato.getNome().toLowerCase()
				.startsWith(busca.toLowerCase());
		boolean subNumber = false;

		for (Telefone tel : contato.getTelefones()) {
			if (tel.getNumero().startsWith(busca.toLowerCase())) {
				subNumber = true;
				break;
			}
		}
		if (subString || subNumber) {
			resultadoBusca.add(contato);
		}
	}

	
	// ---------------Codigo necessario para maniplar o armazenamento dos dados --------------------- //
	
		public void persistirDados() throws IOException {
			FileOutputStream fileSaida = null;
			ObjectOutputStream out = null;
			
			try {
				fileSaida = new FileOutputStream(ARQUIVO);
				out = new ObjectOutputStream(fileSaida);
				out.writeObject(contatos);
				out.writeObject(controladora.getUsuarios());
				out.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}

		public void lerDados() throws IOException {
			FileInputStream fileEntrada = null;
			ObjectInputStream in = null;
			
			try {
				fileEntrada = new FileInputStream(ARQUIVO);
				in = new ObjectInputStream(fileEntrada);
				this.controladora = (Controladora) in.readObject();			
				in.close();
			} catch (IOException ex) {
				this.controladora = new Controladora();
				persistirDados();
			} catch (ClassNotFoundException ex) {
				this.controladora = new Controladora();
				ex.printStackTrace();
			}catch(Exception e) {
				this.controladora = new Controladora();
			}
		}


	// ------------------------------ Getters e Setters  --------------------------------------- //
	
	public ArrayList<Contato> getResultadoBusca() {
		return resultadoBusca;
	}
	
	public String getNewLogin() {
		return newLogin;
	}
	
	public void setNewLogin(String newLogin) {
		this.newLogin = newLogin;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getBusca() {
		return busca;
	}
	
	public void setBusca(String busca) {
		this.busca = busca;
	}
	
	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public void setContatoSelecionado(Contato contatoSelecionado) {
		this.contatoSelecionado = contatoSelecionado;
	}

	public ArrayList<Contato> getContatos() {
		return contatos;
	}

	public Contato getContatoSelecionado() {
		return contatoSelecionado;
	}

	public void setSelectedContact(Contato selectedContact) {
		this.contatoSelecionado = selectedContact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;

	}

	public Contato getContato() {
		return contato;
	}
	
	public Telefone getTelefone() {
		return telefone;
	}

	public String getTipoDeBusca() {
		return tipoDeBusca;
	}

	public void setTipoDeBusca(String tipoDeBusca) {
		this.tipoDeBusca = tipoDeBusca;
	}

	public boolean getTelefoneInvalido() {
		return telefoneInvalido;
	}

	public String getContatoValido() {
		boolean temp = !(this.contato.getNome().equals("") || this.contato
				.getTelefones().isEmpty());
		this.contatoValido = new Boolean(temp).toString();
		return contatoValido;
	}


}