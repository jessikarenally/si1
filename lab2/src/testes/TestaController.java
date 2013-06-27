package testes;

import org.junit.Test;

import junit.framework.Assert;
import controller.ControllerBean;

public class TestaController {
	
	@Test
	public void testaAdd(){
		ControllerBean c1 = new ControllerBean();
		
		
		c1.addContato();
		
	}

}
