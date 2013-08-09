package Testes;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import Model.Contato;
import Model.Telefone;

public class ContatoTest {

	private Contato c1;
	private Contato c2;
	private Contato c3;

	private Telefone t1;
	private Telefone t2;
	private Telefone t3;

	@Before
	public void init() {
		this.c1 = new Contato();
		this.c2 = new Contato();

		this.t1 = new Telefone();
		this.t2 = new Telefone();
		
		this.t3 = new Telefone();
		this.t3.setNumero("12345678");
		
	}



	
	@Test
	public void testConstrutor() {
		//Inicializado Contato default;
		Assert.assertEquals(c1.getNome(), "");
		Assert.assertEquals(c1.getIdade(), "");
		Assert.assertEquals("Não existe descrição para este contato.", c1.getDescricao());
		Assert.assertEquals(c1.getEmails(), new ArrayList<String>());
		Assert.assertEquals(c1.getTelefones(), new ArrayList<Telefone>());
		Assert.assertEquals(c1.getQuantidadeTelefones(), (new Integer(0)).toString());
		
		//Inicializando Contato com entrada;
		try {
			this.c3 = new Contato("Teste", t3);
		} catch (IllegalArgumentException e) {
			Assert.fail();
		}
		Assert.assertEquals(c3.getNome(), "Teste");
		Assert.assertEquals(c3.getIdade(), "");
		Assert.assertEquals(c3.getEmails(), new ArrayList<String>());
		//Testando os telefones cadastrados
		ArrayList<Telefone> teste = new ArrayList<Telefone>();
		teste.add(t3);
		Assert.assertEquals(c3.getTelefones(), teste);
		Assert.assertEquals(c3.getQuantidadeTelefones(), (new Integer(1)).toString());
	}

	@Test
	public void testAddsERemoves(){
		List<Telefone> telefones = new ArrayList<Telefone>();
		//Telefone1 é inválido pois não tem número
		try {
			c1.addTelefone(t1);
			Assert.fail();
		} catch (IllegalArgumentException e) {
		}
		//Telefone3 é válido pois tem número
		try {
			c1.addTelefone(t3);
			telefones.add(t3);
		} catch (IllegalArgumentException e) {
			Assert.fail();
		}

		Assert.assertEquals(c1.getTelefones(), telefones);
		
		//Como é o único telefone, não poderá remover
		c1.removeTelefone(t3);
		Assert.assertEquals(c1.getTelefones(), telefones);
		
		//Adicionando outro telefone válido
		t1.setNumero("12345677");
		try {
			c1.addTelefone(t1);
			telefones.add(t1);
		} catch (IllegalArgumentException e) {
			Assert.fail();
		}
		
		Assert.assertEquals(c1.getTelefones(), telefones);
		
		//Não adiciona telefones repetidos
		t2.setNumero("12345678"); 
		try {
			c1.addTelefone(t2);
			Assert.fail();
		} catch (IllegalArgumentException e) {
		}
		
		Assert.assertEquals(c1.getTelefones(), telefones);
		c1.removeTelefone(t3);
		
		c1.addEmail("EMAIL");
		List<String> emails = new ArrayList<String>();
		emails.add("EMAIL");
		Assert.assertEquals(c1.getEmails(), emails);
		
		c1.removeEmail("EMAIL");
		emails.remove("EMAIL");
		Assert.assertEquals(c1.getEmails(), emails);
		
	}
	
}
