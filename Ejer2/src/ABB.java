import java.util.concurrent.TimeUnit;

@SuppressWarnings("hiding")
public class ABB<Integer> extends AB<Integer> {

	// Este insertar fue modificado para que el árbol sea binario de busqueda
	@Override
	protected void insertar(Nodo<Integer> padre, Nodo<Integer> nuevo) {
		if (padre.izq == null && (int) padre.info > (int) nuevo.info) {
			padre.izq = nuevo;
		} else if (padre.der == null && (int) padre.info < (int) nuevo.info) {
			padre.der = nuevo;
		}

		else {

			if ((int) padre.info > (int) nuevo.info) {
				insertar(padre.izq, nuevo);
			} else {
				insertar(padre.der, nuevo);
			}

		}
	}

	@Override
	public String toString() {

		if (this.raiz.izq != null && this.raiz.der != null) {
			return "\n     " + this.raiz.info.toString() + "\n ( " + this.raiz.izq.info.toString() + "  "
					+ this.raiz.der.info.toString() + " )\n" + toString(this.raiz.izq) + toString(this.raiz.der);
		} else if (this.raiz.izq == null && this.raiz.der != null) {
			return "\n     " + this.raiz.info.toString() + "\n (    " + this.raiz.der.info.toString() + " )\n"
					+ toString(this.raiz.der);
		}

		else if (this.raiz.izq != null && this.raiz.der == null) {
			return "\n     " + this.raiz.info.toString() + "\n ( " + this.raiz.izq.info.toString() + "    )\n"
					+ toString(this.raiz.izq);
		} else {
			return "\n     " + this.raiz.info.toString() + "\n(    )\n";
		}

	}

	private String toString(Nodo<Integer> nodo) {

		if (nodo.izq != null && nodo.der != null) {
			return "\n     " + nodo.info.toString() + "\n" + "( " + nodo.izq.info.toString() + " "
					+ nodo.der.info.toString() + " )\n" + toString(nodo.izq) + " " + toString(nodo.der);
		} else if (nodo.izq != null && nodo.der == null) {
			return "\n     " + nodo.info.toString() + "\n" + " ( " + nodo.izq.info.toString() + "     )\n"
					+ toString(nodo.izq) + " ";
		} else if (nodo.izq == null && nodo.der != null) {
			return "\n     " + nodo.info.toString() + "\n" + " (     " + nodo.der.info.toString() + " )\n" + "  "
					+ toString(nodo.der);
		} else {
			return "\n     " + nodo.info.toString() + "\n (       )\n";
		}

	}

	public boolean balanceado() {

		if (this.raiz == null) {
			return true;
		} else {
			return balanceado(this.raiz);
		}

	}

	public boolean balanceado(Nodo<Integer> nodo) {
		boolean acumulador = true;
		int alturaIzquierda = 0;
		int alturaDerecha = 0;

		if (nodo.der != null) {
			alturaDerecha = altura(nodo.der);
			acumulador = acumulador && balanceado(nodo.der);

		}

		if (nodo.izq != null) {
			alturaIzquierda = altura(nodo.izq);
			acumulador = acumulador && balanceado(nodo.izq);
		}

		int resta = alturaIzquierda - alturaDerecha;

		if (resta < 0) {
			// resta + resta es cero. + resta es el absoluto o módulo (en caso de ser
			// negativo)
			resta = resta + resta + resta;
		}

		acumulador = acumulador && resta <= 1;
		return acumulador;
	}

	public void rebalancear() {
		while (!this.balanceado()) {
			//System.out.print(this.toString());
			if (this.raiz != null) {

				if (altura(this.raiz.izq) - altura(this.raiz.der) > 2) {
					//System.out.print("\nDesde la raiz es muy pesado por izquierda. Recambiando hacia derecha");
					this.raiz = recambioDerecha(this.raiz);

				} else if (altura(this.raiz.izq) - altura(this.raiz.der) < -2) {
					//System.out.print("\nDesde la raiz es muy pesado por derecha. Recambiando hacia izquierda");
					this.raiz = recambioIzquierda(this.raiz);

				}

				else if (altura(this.raiz.izq) - altura(this.raiz.der) == 2) {
					if (balanceado(this.raiz.izq)) {
						//System.out.print("\nDesde la raiz pasado por 1 por izquierda. Recambiando hacia derecha. Altura 3");
						this.raiz = recambioDerecha(this.raiz);
					} else {
						//System.out.print("\nDesde la raiz pasado por 1 por izquierda. Balancear la izquierda. Altura " + altura(this.raiz));
						this.raiz.izq = this.balancear(this.raiz.izq);
					}

				} else if (altura(this.raiz.izq) - altura(this.raiz.der) == -2) {
					if (balanceado(this.raiz.der)) {
						//System.out.print("\nDesde la raiz pasado por 1 por derecha. Recambiando hacia izquierda. Altura 3");
						this.raiz = recambioIzquierda(this.raiz);
					} else {
						//System.out.print("\nDesde la raiz pasado por 1 por derecha. Balancear la derecha. Altura " +  altura(this.raiz));
						this.raiz.der = this.balancear(this.raiz.der);
					}

				} else {

					if (!balanceado(this.raiz.izq)) {
						this.raiz.izq = this.balancear(this.raiz.izq);
					} else {
						this.raiz.der = this.balancear(this.raiz.der);
					}

				}

			}

		}
	}

	private Nodo<Integer> balancear(Nodo<Integer> nodo) {
		//System.out.print("\nTrabajando con " + nodo.info);

		

		if (altura(nodo.izq) - altura(nodo.der) > 2) {
			//System.out.print("\nDesde este nodo es muy pesado por izquierda. Balancear la izquierda Altura " + altura(nodo));
			if (nodo.der != null) {
				nodo.izq = balancear(nodo.izq);
			} else {
				nodo = recambioDerecha(nodo);
			}

		} else if (altura(nodo.izq) - altura(nodo.der) < -2) {
			//System.out.print("\nDesde este nodo es muy pesado por derecha. Balancear la derecha Altura " + altura(nodo));
			if (nodo.izq != null) {
				nodo.der = balancear(nodo.der);
			} else {
				nodo = recambioIzquierda(nodo);
			}

		}

		else if (altura(nodo.izq) - altura(nodo.der) == -2) {
			//System.out.print("\nDesde este nodo pasado por 1 por derecha. Recambiando hacia izquierda. Altura " + altura(nodo));
			nodo = recambioIzquierda(nodo);

		}

		else if (altura(nodo.izq) - altura(nodo.der) == 2) {
			//System.out.print("\nDesde este nodo pasado por 1 por izquierda. Recambiando hacia derecha. Altura " + altura(nodo));
			nodo = recambioDerecha(nodo);
		}

		if (nodo.izq != null) {
			if (!balanceado(nodo.izq)) {
				nodo.izq = balancear(nodo.izq);
			}
		}

		if (nodo.der != null) {
			if (!balanceado(nodo.der)) {
				nodo.der = balancear(nodo.der);
			}
		}

		return nodo;
	}

	private Nodo<Integer> recambioIzquierda(Nodo<Integer> nodo) {

		Nodo<Integer> nodoTemporal = nodo;

		Nodo<Integer> nodoTemporalHijo = nodo.der.izq;

		nodo = nodoTemporal.der;

		nodo.izq = nodoTemporal;

		nodo.izq.der = null;

		if (nodoTemporalHijo != null) {
			insertar(this.raiz, nodoTemporalHijo);
		}

		return nodo;

	}

	private Nodo<Integer> recambioDerecha(Nodo<Integer> nodo) {

		Nodo<Integer> nodoTemporal = nodo;
		Nodo<Integer> nodoTemporalHijo = nodo.izq.der;

		nodo = nodoTemporal.izq;

		nodo.der = nodoTemporal;

		nodo.der.izq = null;

		if (nodoTemporalHijo != null) {
			insertar(this.raiz, nodoTemporalHijo);
		}

		return nodo;

	}

	public Integer iesimo(int i) {

		if (cantNodos(raiz) <= i || i < 0) {
			throw new RuntimeException("No existe dicho iesimo. Te pasaste maestro/a");
		}

		if (i == 0 && raiz.izq == null) {
			return raiz.info;
		} else {

			if (cantNodos(raiz.izq) < i) {

				//System.out.println("En esta raiz estamos en la " + cantNodos(raiz.izq) + " posición. Buscaremos por derecha");
				return iesimo(raiz.der, i, cantNodos(raiz.izq) + 1);
			} else if (cantNodos(raiz.izq) == i) {
				//System.out.println("Embocado en la primera de cambio");
				return raiz.info;
			} else {

				//System.out.println("En esta raiz estamos en la " + cantNodos(raiz.izq) + " posición. Buscaremos por izquierda");
				return iesimo(raiz.izq, i, 0);
			}

		}
	}

	private Integer iesimo(Nodo<Integer> nodo, int i, int contador) {
		int suma;
		if (nodo.izq != null) {

			suma = cantNodos(nodo.izq) + contador;
			if (cantNodos(nodo.izq) + contador < i) {

				//System.out.println("En este nodo estamos en la " + suma + " posicion. Buscaremos por derecha");
				return iesimo(nodo.der, i, suma + 1);

			} else if (cantNodos(nodo.izq) + contador == i) {

				//System.out.println("En este nodo estamos en la " + suma + " posicion. Lo encontramos");
				return nodo.info;

			} else {

				//System.out.println("En este nodo estamos en la " + suma + " posicion. Buscaremos por izquierda");
				return iesimo(nodo.izq, i, contador);
			}

		} else {

			if (contador < i) {
				//System.out.println("No hay mas lugares por izquierda y este no es. Buscaremos por derecha. Contador = " + contador);
				return iesimo(nodo.der, i, contador + 1);
			} else if (contador == i) {
				//System.out.println("No hay mas lugares por izquierda y este si es. Lo encontramos. Contador = " + contador);
				return nodo.info;
			} else {
				return nodo.info;
			}

		}

	}

}
