
import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

public class TestFirstClass extends TestCase {
	
	@Test
	public void test(){
		FirstClass f1 = new FirstClass();
		Assert.assertEquals("Primeiro lab criado", f1.xpto());
	}
}
