import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestEj2Test_nuestro {

	ABB<Integer> abDesbalanceado;

	@Before
	public void setUp() throws Exception {
		abDesbalanceado = new ABB<Integer>();
		abDesbalanceado.insertar(9);
		abDesbalanceado.insertar(7);
		abDesbalanceado.insertar(11);
		abDesbalanceado.insertar(2);
		abDesbalanceado.insertar(1);
		abDesbalanceado.insertar(3);
		abDesbalanceado.insertar(4);
		abDesbalanceado.insertar(-4);

	}
	
	@Test
	public void testRebalancear() {
		System.out.print(abDesbalanceado.toString());
		abDesbalanceado.rebalancear();
		
		System.out.print("Deberia estar balanceado ya");
		System.out.print(abDesbalanceado.toString());
		assertTrue(abDesbalanceado.balanceado());
	}

	@Test
	public void iesimo() {
			
		assertEquals(abDesbalanceado.iesimo(0), new Integer(-4));
		assertEquals(abDesbalanceado.iesimo(1), new Integer(1));
		assertEquals(abDesbalanceado.iesimo(2), new Integer(2));
		assertEquals(abDesbalanceado.iesimo(3), new Integer(3));
		assertEquals(abDesbalanceado.iesimo(4), new Integer(4));
		assertEquals(abDesbalanceado.iesimo(5), new Integer(7));
		assertEquals(abDesbalanceado.iesimo(6), new Integer(9));
		assertEquals(abDesbalanceado.iesimo(7), new Integer(11));
	}
		

}
