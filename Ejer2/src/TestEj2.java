import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;



public class TestEj2 {
	ABB<Integer> abBalanceado, abVacio, abDesbalanceado;

	@Before
	public void setUp() throws Exception {
		abVacio = new ABB<Integer>();
		abDesbalanceado = new ABB<Integer>();
		abDesbalanceado.insertar(5);
		abDesbalanceado.insertar(3);
		abDesbalanceado.insertar(1);
		abBalanceado = new ABB<Integer>();
		abBalanceado.insertar(8);
		abBalanceado.insertar(3);
		abBalanceado.insertar(10);
		abBalanceado.insertar(1);
		abBalanceado.insertar(6);
		abBalanceado.insertar(4);
		abBalanceado.insertar(7);
		abBalanceado.insertar(14);
		abBalanceado.insertar(9);
	}

	@Test
	public void testBalanceado() {
		assertTrue(abBalanceado.balanceado());
		assertTrue(abVacio.balanceado());
		assertFalse(abDesbalanceado.balanceado());
	}

	@Test
	public void testRebalancear() {
		abDesbalanceado.rebalancear();
		assertTrue(abDesbalanceado.balanceado());
	}

	 
	 
	@Test
	public void testIesimo() {
		assertEquals(abBalanceado.iesimo(0), new Integer(1));
		assertEquals(abBalanceado.iesimo(5), new Integer(8));
		assertEquals(abBalanceado.iesimo(8), new Integer(14));
		boolean thrown = false;
		try {
			abBalanceado.iesimo(88);
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);
	}

	/*
	 *
	@Test
	public void testIrep() {
		assertTrue(abVacio.irep());
		assertTrue(abDesbalanceado.irep());
		assertTrue(abBalanceado.irep());
		abBalanceado.romperIrep();
		assertFalse(abBalanceado.irep());
	}
	*/
	
}