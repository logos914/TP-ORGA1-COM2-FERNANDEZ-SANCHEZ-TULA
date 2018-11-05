import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestEj1_nuestro {
	private Jenga jenga1,jenga2,jenga3;

	@Before
	public void setUp() {
		jenga1 = new Jenga(4, "j0", "j1");
		jenga2 = new Jenga(4, "j0", "j1");
		jenga3 = new Jenga(21, "j0", "j1");
	}



	@Test
	//PERDER ALEVOSAMENTE (QUITAR FICHA DE COSTADO Y CENTRAL)
	public void test1() {
		
		jenga1.mover(0, 1);
		jenga1.mover(0, 2);
		
		System.out.println(jenga1.ganador());
		// deberia haberse caido el jenga!
		assertTrue(!jenga1.ganador().equals(""));
	}
	
	@Test
	//PERDER ALEVOSAMENTE (QUITAR FICHA DE CENTRAL Y COSTADO)
	// SIN EL SUBCASO 3 EL JENGA ANTES CONTINUABA DE A PIE
	public void test2() {
		
		jenga2.mover(0, 2);
		jenga2.mover(0, 1);
		
		System.out.println(jenga2.ganador());
		// deberia haberse caido el jenga!
		assertTrue(!jenga2.ganador().equals(""));
	}
	
	@Test
	//DEBERIA CAER PUESTO QUE LA PROBABILIDAD ES 100 EN CONTRA (5 * 20)
	public void test3() {
		
		jenga3.mover(0, 1);
		jenga3.mover(0, 0);
		
		System.out.println(jenga3.ganador());
		// deberia haberse caido el jenga!
		assertTrue(!jenga3.ganador().equals(""));
	}
	
	
}
