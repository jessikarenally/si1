package testes;

import org.junit.Test;

import junit.framework.Assert;
import controller.ControllerBean;

public class TestaController {
	
	@Test
	public void testaAdd(){
		ControllerBean c1 = new ControllerBean();
		
		
		c1.addContato("Joao", "876523456789", "oi", "", "");
		Assert.assertEquals(true, c1.hasContato("Joao"));
		
		Assert.assertEquals(false, c1.hasContato("joana"));
	}

}
