import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Jenga {

	private String jugador1, jugador2;
	private Torre torre;
	private String siguienteTurno;
	private Boolean jugando;

	Jenga(int niveles, String jug1, String jug2) {

		this.jugador1 = jug1;
		this.jugador2 = jug2;
		this.torre = new Torre(niveles);
		this.siguienteTurno = this.jugador1;
		this.jugando = true;

	}

	public String ganador() {
		if (!this.jugando) {
			if (this.siguienteTurno.equals(this.jugador1)) {
				return this.jugador1;
			} else {
				return this.jugador2;
			}

		}
		return "";
	}

	/*
	 * EJECUTA LAS ACCIONES DE UN TURNO DE JUEGO
	 * 
	 */
	public void jugar() {

		
		/*
		 * INTERFAZ PARA JUGAR MANUALMENTE
		  
		  @SuppressWarnings("resource")
		Scanner entradaTeclado = new Scanner(System.in);
		  System.out.print(this.siguienteTurno + " Indicá el nivel a mover: "); int
		  nivel = entradaTeclado.nextInt(); System.out.print(this.siguienteTurno +
		  " Indicá la ficha a mover (de 0 a 2): "); int pos = entradaTeclado.nextInt();
		 */
		
		
		
		
		
		

		/*
		 * INTERFAZ PARA JUEGO AUTOMATICO
		*/
		Random automatico = new Random();
		int nivel = automatico.nextInt(this.torre.ultimoNivel() - 1);
		int pos = automatico.nextInt(2);
		

		try {

			if (this.mover(nivel, pos)) {

				if (this.siguienteTurno.equals(this.jugador1)) {
					this.siguienteTurno = this.jugador2;
				} else {
					this.siguienteTurno = this.jugador1;
				}
				// PAUSA LA EJECUCIÓN UNO SEGUNDO PARA VER EL RESULTADO DEL MOVIMIENTO
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
			}

		} catch (Exception e) {
			System.out.print("Che, " + this.siguienteTurno + " hiciste macana, elegí mover una ficha válida");
			
			// PAUSA LA EJECUCIÓN DOS SEGUNDOS PARA VER EL RESULTADO DEL NO MOVIMIENTO
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e1) {

				e1.printStackTrace();
			}
		}
		
		 
	}

	/*
	 * REALIZA LAS CALCULACIONES DE PROBABILIDADES Y EJECUTA DICHO AZAR
	 * 
	 * En encarga de calcular si retirar dicha pieza genera caida de la torre o no.
	 * Calculo la probabilidad de caida. Solicita la ejecución de dicho azar. Indica
	 * verdadero si se puede realizar el movimiento y sigue de pie la torre. Indica
	 * falso si al realizar el movimiento hay caida de torre.
	 * 
	 * 
	 */

	private boolean fisicaDeCaidas(int pisosPorEncima, boolean esFichaMedio, boolean quedanDos) {

		int probabilidadCaida = 0;
		int nivelActual = this.torre.ultimoNivel()- pisosPorEncima;

		if (!esFichaMedio && quedanDos) {
			probabilidadCaida = 1 * pisosPorEncima;
		}

		if (esFichaMedio && quedanDos) {
			probabilidadCaida = 0;
		}

		if (!esFichaMedio && !quedanDos) {
			
			if (this.torre.existeFicha(nivelActual, 1)) {
				probabilidadCaida = 5 * pisosPorEncima;
			} else {
				return false;
			}
			
			
			
			
		}

		if (esFichaMedio && !quedanDos) {
			return false;
		}
		
		

		// System.out.println("la probabilidad de perder es " + probabilidadCaida);

		if (this.bolillero(probabilidadCaida)) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 *
	 * BOLILLERO EJECUTA UN AZAR ENTRE UN RANDOM DE 0 A 99 El parámetro indica la
	 * longitud de un subconjunto de 0 a 99 que es la posibilidad de perder.
	 * 
	 * Esta función genera otro random interno, para desplazar el subconjunto que
	 * hace perder, para que pueda desplazarse dentro del conjunto 0 a 99.
	 * 
	 * Esto permite generar, suponiendo una probabilidad del 25%, que haya
	 * subconjuntos perdedores que comienzen en 0 a 24, y otra ejecución de 74 a 99.
	 * 
	 * Retorna verdadero cuando se gana, y falso cuando el bolillero salió dentro
	 * del subconjunto de números perdedores.
	 * 
	 * 
	 * 
	 */

	private boolean bolillero(int probabilidadCaida) {

		Random ran = new Random();
		int inicioRangoCaida = ran.nextInt(99 - probabilidadCaida - 1);
		int finRangoCaida = inicioRangoCaida + probabilidadCaida - 1;

		int bolilla = ran.nextInt(99);

		/*
		 * PERMITE DEBUGGEAR LAS PROBABILIDADES System.out.println("Se pierde entre  " +
		 * inicioRangoCaida + " y " + finRangoCaida); System.out.println("Salió el " +
		 * bolilla);
		 * 
		 */

		if (bolilla < inicioRangoCaida) {

			return true;
		}

		else if (bolilla > finRangoCaida) {

			return true;
		}

		else if (bolilla >= inicioRangoCaida && bolilla <= finRangoCaida) {

			return false;
		}

		else {

			return false;
		}

	}

	public int altura() {
		return this.torre.ultimoNivel();
	}

	/*
	 * SE ENCARGA DE REALIZAR LAS CALCULACIONES PARA EL MOVIMIENTO INDICADO POR UN
	 * JUGADOR
	 * 
	 * Revisar la existencia de la ficha en el lugar indicado. Revisa si el
	 * movimiento se puede realizar, o si se debe perder por mover la única ficha
	 * del nivel. Realiza el movimiento y revisa si mantiene la torre en pie o no.
	 * 
	 *
	 * 
	 */

	public boolean mover(int nivel, int pos) {

		// Solo se puede realizar movimiento si la ficha solicitada existe en el lugar
		// indicado
		if (this.torre.existeFicha(nivel, pos) && nivel < this.torre.ultimoNivel()) {

			// Si existe movimiento posible (2 fichas o más en nivel)
			if (this.torre.hayMovimientoPosible(nivel)) {

				// Calculo de probabilidades, y ejecucción de dicho azar. Si el movimiento es
				// aprobado sin caida, se realiza normalmente
				if (this.fisicaDeCaidas(this.torre.ultimoNivel() - nivel, Torre.esFichaCentral(pos),
						this.torre.hay3EnNivel(nivel))) {

					this.torre.quitarFicha(nivel, pos);
					this.torre.agregarFicha();
					return true;

					// Si el movimiento no es aprobado, entonces se debe indicar que cayó la torre
				} else {

					this.torre.quitarFicha(nivel, pos);
					this.jugando = false;
					return true;

				}

				// No existe el movimiento (1 sola ficha). Siempre debe perder o debe tirar la
				// torre
			} else {
				this.jugando = false;
				return true;
			}

			// Si la ficha solicitado no existe, se lanza excepción
		} else {
			throw new RuntimeException("LA FICHA ELEGIDA PARA MOVER, NO EXISTE");
		}

	}

	@Override
	public String toString() {
		StringBuilder cadena;
		if (this.ganador().equals("")) {
			cadena = new StringBuilder(
					"=============================\nAHORA ESTA JUGANDO: "
							+ this.siguienteTurno + "\n\n" + this.torre.toString());

		} else {
			cadena = new StringBuilder(
					"=============================\n" + this.torre.toString());

		}
		cadena.insert(cadena.length() - 1, "\n=============================");
		return cadena.toString();
	}

	protected int primerNivelPosible() {
		// TODO Auto-generated method stub
		return 0;
	}

	protected void quitar(int nivel, int i) {
		this.mover(nivel, i);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		@SuppressWarnings("resource")
		Scanner entradaTeclado = new Scanner(System.in);
	
		
		// INTERFACE PARA CONSULTAR NOMBRES DE JUGADORES Y DIFICUTAD
		System.out.print("Ingrese el nombre del jugador 1: ");
		String jugador1 = entradaTeclado.next();
		System.out.print("\nIngrese el nombre del jugador 2: ");
		String jugador2 = entradaTeclado.next();
		System.out.print("\nIngrese la altura inicial del Jenga: ");
		int niveles = entradaTeclado.nextInt();
		
		
		
		
		Jenga unJenga = new Jenga(niveles,jugador1, jugador2);
		
		
		
		
	
while (unJenga.ganador().equals("")) {
	

	System.out.print("\n" + unJenga);
	unJenga.jugar();
	
	// Para limpiar un poco la salida de Consola luego de mostrar cada jenga
	for (int i = 0; i <5; i++) {
		System.out.print("\n");
	}

	
}


System.out.print("\n" + unJenga);
System.out.print("\n=============================\n");
System.out.print("\n Cayó el Jenga, el ganad@r es: " + unJenga.ganador());
		
				
		
	
				
				
				
		

	}

}
