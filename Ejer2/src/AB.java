
@SuppressWarnings("hiding")
public class AB<Integer> {

	protected Nodo<Integer> raiz;

	public class Nodo<Integer> { // clase interna
		protected Integer info;
		protected Nodo<Integer> izq;
		protected Nodo<Integer> der;

		public Nodo(Integer info) {
			this.info = info;
		}

		@Override
		public String toString() {
			return info.toString();
		}

	}

	public void insertar(Integer elem) {
		Nodo<Integer> nuevo = new Nodo<Integer>(elem);
		if (raiz == null) {
			raiz = nuevo;
		} else {
			insertar(raiz, nuevo);
		}

	}

	protected void insertar(Nodo<Integer> padre, Nodo<Integer> nuevo) {
		if (padre.izq == null) {
			padre.izq = nuevo;
		} else {
			if (padre.der == null) {
				padre.der = nuevo;
			} else {
				// Este arbol construye siempre a izquierda
				insertar(padre.izq, nuevo);
			}

		}

	}

	public Nodo<Integer> buscar(Integer elem) {
		if (raiz == null) {
			return null;

		} else {
			return buscar(raiz, elem);
		}
	}

	protected Nodo<Integer> buscar(Nodo<Integer> nodo, Integer elem) {
		if (nodo.info.equals(elem)) {
			return nodo;
		} else {
			Nodo<Integer> izq = null;
			Nodo<Integer> der = null;

			if (nodo.izq != null) {
				izq = buscar(nodo.izq, elem);
			}

			if (nodo.der != null) {
				der = buscar(nodo.der, elem);
			}

			if (izq != null) {
				return izq;
			}

			else if (der != null) {
				return der;
			} else {
				return null;
			}

		}
	}

	public int cantNodos() {
		return (raiz == null) ? 0 : cantNodos(raiz);
	}

	protected int cantNodosIzquierda(Nodo<Integer> nodo) {
		int cantIzq;
		if (nodo.izq == null) {
			cantIzq = 0;
		} else {
			cantIzq = cantNodosIzquierda(nodo.izq);
		}
		return 1 + cantIzq;

	}

	protected int cantNodosDerecha(Nodo<Integer> nodo) {
		int cantDer;
		if (nodo.der == null) {
			cantDer = 0;
		} else {
			cantDer = cantNodosDerecha(nodo.der);
		}
		return 1 + cantDer;

	}

	protected int cantNodos(Nodo<Integer> nodo) {

		int cantIzq;
		int cantDer;

		if (nodo.izq == null) {
			cantIzq = 0;
		} else {
			cantIzq = cantNodos(nodo.izq);
		}

		if (nodo.der == null) {
			cantDer = 0;
		} else {
			cantDer = cantNodos(nodo.der);
		}

		return 1 + cantIzq + cantDer;
	}

	public int altura() {

		if (raiz == null) {
			return 0;
		} else {
			return altura(raiz);
		}

	}

	protected int altura(Nodo<Integer> nodo) {

		int altIzq = 0;
		int altDer = 0;
		if (nodo != null) {
			if (nodo.izq == null) {
				altIzq = 0;
			} else {
				altIzq = altura(nodo.izq);
			}

			if (nodo.der == null) {
				altDer = 0;
			} else {
				altDer = altura(nodo.der);
			}

			return 1 + Math.max(altIzq, altDer);

		} else {
			return 0;
		}
	}

}
