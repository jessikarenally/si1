package Testes;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import Bean.Agenda;
import Model.Contato;
import Model.Telefone;


public class AgendaTest {
	Agenda agenda;
	Contato c1, c2;
	Telefone t1, t2;

	@Before
	public void init() {
		this.agenda = new Agenda();
		this.c1 = new Contato();
		this.c2 = new Contato();
		this.t1 = new Telefone();
		this.t2 = new Telefone();
	}

	@Test
	public void testaddTelefone() {
		try {
			t1.setOperadoraLigar("82");
			t1.setOperadora("oi");
			c1.addTelefone(t1);
			fail("not InvalidNumberException");
		} catch (IllegalArgumentException e) {
		}

		try {
			t1.setCodigoRegional("13");
			c1.addTelefone(t1);
			fail("not InvalidNumberException");
		} catch (IllegalArgumentException e) {
		}

		try {
			t1.setNumero("88888888");
			c1.addTelefone(t1);
		} catch (IllegalArgumentException e) {
			fail("InvalidNumberException");
		}

	}

	@Test
	public void testContatoValido() {
		Assert.assertFalse(c1.isContatoValido());
		t1.setOperadora("oi");
		t1.setNumero("888888888");
		try {
			c1.addTelefone(t1);
		} catch (IllegalArgumentException e) {}
		
		Assert.assertFalse(c1.isContatoValido());

	}
	
	@Test
	public void testTelefoneValido() {
		Assert.assertFalse(t1.ehTelefoneValido());
		
		t1.setNumero("8888");
		Assert.assertFalse(t1.ehTelefoneValido());
		
		t1.setNumero("88888888");
		Assert.assertTrue(t1.ehTelefoneValido());
		
		t2.setOperadora("31");
		Assert.assertFalse(t2.ehTelefoneValido());
		
		t2.setCodigoRegional("44");
		Assert.assertFalse(t2.ehTelefoneValido());
		
		t2.setNumero("88888888");
		Assert.assertFalse(t2.ehTelefoneValido());
		
		t2.setOperadoraLigar("21");
		Assert.assertTrue(t2.ehTelefoneValido());
		
	}
	@Test
	public void testBuscaNomeTel() {
		agenda.contatos = new ArrayList<Contato>();
		agenda.getContatos().add(c1);
		
		agenda.setBusca("p");
		Assert.assertEquals(1, agenda.getContatos().size());
		
		agenda.setBusca("l");
		Assert.assertEquals(1, agenda.getContatos().size());
		
		agenda.setBusca("op");
		Assert.assertEquals(1, agenda.getContatos().size());
		
		agenda.setBusca("99");
		Assert.assertEquals(1, agenda.getContatos().size());
	}
	@Test
	public void testBuscaIdade() {
		agenda.setTipoDeBusca("2");
		agenda.setBusca("40");
		Assert.assertEquals(0, agenda.getResultadoBusca().size());
		
		agenda.setTipoDeBusca("3");
		Assert.assertEquals(0, agenda.getResultadoBusca().size());
		
		agenda.setTipoDeBusca("4");
		Assert.assertEquals(0, agenda.getResultadoBusca().size());
		
		agenda.setTipoDeBusca("2");
		agenda.setBusca("100");
		Assert.assertEquals(0, agenda.getResultadoBusca().size());
		
		agenda.setTipoDeBusca("4");
		agenda.setBusca("10");
		Assert.assertEquals(0, agenda.getResultadoBusca().size());
	}

}