import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestEj1 {
	private Jenga jenga1, jenga2;

	@Before
	public void setUp() {
		jenga1 = new Jenga(10, "j0", "j1");
		jenga2 = new Jenga(10, "jugador0", "jugador1");
	}

	@Test
	public void test1() {
		int alturaInicial = jenga1.altura();
		jenga1.jugar();
		jenga1.jugar();
		jenga1.jugar();
		System.out.println(alturaInicial +","+ jenga1.altura());
		// deberia cambiar la altura
		assertTrue(alturaInicial != jenga1.altura());
	}

	@Test
	public void test2() {
		int nivel = jenga2.primerNivelPosible();
		jenga2.quitar(nivel, 0);
		jenga2.quitar(nivel, 1);
		jenga2.quitar(nivel, 2);
		System.out.println(jenga2.ganador());
		// deberia haberse caido el jenga!
		assertTrue(!jenga2.ganador().equals(""));
	}
}
