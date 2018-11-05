import java.util.ArrayList;
import java.util.Iterator;

public class Torre {

	// ATRIBUTOS
	boolean estadoDePie;
	ArrayList<Nivel> niveles = new ArrayList<Nivel>();

	// constructor
	Torre(int nivelesParaCrear) {
		for (int i = 0; i < nivelesParaCrear; i++) {
			Nivel nivelParaConstruir = new Nivel(true);
			this.niveles.add(nivelParaConstruir);
		}

		this.estadoDePie = true;
	}

	
	
	
	// CLASE INTERNA NIVEL
	class Nivel {
		boolean[] elementos;

		// Constructor
		public Nivel(boolean tipo) {

			// Un nivel entero
			if (tipo) {

				boolean[] nivelPrevio = { true, true, true };
				this.elementos = nivelPrevio;

				// Un nivel básico
			} else {

				boolean[] nivelPrevio = { true, false, false };
				this.elementos = nivelPrevio;

			}

		}

		private boolean obtenerElemento(int pos) {

			return this.elementos[pos];

		}

		private void retirar(int pos) {

			this.elementos[pos] = false;
		}

		private void agregar(int pos) {

			this.elementos[pos] = true;
		}

	}
		
		

		public int ultimoNivel() {
			return this.niveles.size() - 1;
		}

		
		static boolean esFichaCentral(int pos) {
			if (pos == 1) {
				return true;
			} else {
				return false;
			}
		}

		public boolean hay3EnNivel(int nivel) {

			int contador = 0;

			for (int i = 0; i < this.niveles.get(nivel).elementos.length; i++) {
				if (this.niveles.get(nivel).obtenerElemento(i)) {
					contador++;
				}

			}

			if (contador == 3) {
				return true;

			} else {
				return false;
			}

		}

	

	public void quitarFicha(int nivel, int pos) {

		this.niveles.get(nivel).retirar(pos);


	}

	
	
	public  void agregarFicha() {

		if (this.hay3EnNivel(this.ultimoNivel())) {
			Nivel nuevoNivel = new Nivel(false);
			this.niveles.add(nuevoNivel);
		} else {

			Nivel ultimoNivel;
			ultimoNivel = this.niveles.get(this.ultimoNivel());

			if (!ultimoNivel.obtenerElemento(1)) {
				ultimoNivel.agregar(1);
				
			}

			else if (!ultimoNivel.obtenerElemento(2)) {
				ultimoNivel.agregar(2);
				
			}

		}

	}

	public boolean hayMovimientoPosible(int nivel) {

		Nivel nivelActual = this.niveles.get(nivel);
		int contador = 0;

		for (int i = 0; i < nivelActual.elementos.length; i++) {
			if (nivelActual.obtenerElemento(i)) {
				contador++;
			}

		}

		if (contador == 1) {
			
			return false;

		} else {
			
			return true;
		}

	}

	public boolean existeFicha(int nivel, int pos) {
		if (this.niveles.get(nivel).obtenerElemento(pos)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {

		StringBuilder cadena =  new StringBuilder ("");
		Integer contador = 0;
		Iterator<Nivel> it = this.niveles.iterator();
		while (it.hasNext()) {
			
			
			String numero;
			if (contador < 10) {
				numero = "0"+ contador.toString();
			} else {
				numero = contador.toString();
			}
			StringBuilder subCadena = new StringBuilder("" + numero + " => ");
			Nivel nivelActual;
			nivelActual = it.next();
			
			for (int i = 0; i < nivelActual.elementos.length; i++) {

				if (nivelActual.obtenerElemento(i)) {
					subCadena.insert(subCadena.length()-1, " |X| ");
					
				} else {
					subCadena.insert(subCadena.length()-1, " | | ");
					
				}

			}
			cadena = cadena.insert(0, subCadena.toString() + "\n"); 
			contador++;

		}

		return cadena.toString();

	}
	
	

}
