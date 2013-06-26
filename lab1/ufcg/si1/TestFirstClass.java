package ufcg.si1;

import junit.framework.Assert;

import org.junit.Test;

public class TestFirstClass {
	
	@Test
	public void test(){
		FirstClass f1 = new FirstClass();
		Assert.assertEquals("Primeiro lab criado", f1.xpto());
	}
}
