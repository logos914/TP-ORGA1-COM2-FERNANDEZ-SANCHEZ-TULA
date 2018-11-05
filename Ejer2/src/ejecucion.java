
public class ejecucion {

	public static void main(String[] args) {
	
		
		ABB<Integer> abDesbalanceado;
		
		abDesbalanceado = new ABB<Integer>();
		abDesbalanceado.insertar(7);
		abDesbalanceado.insertar(5);
		abDesbalanceado.insertar(3);
		abDesbalanceado.insertar(2);
		abDesbalanceado.insertar(1);
		abDesbalanceado.insertar(0);
		abDesbalanceado.insertar(-1);
		abDesbalanceado.insertar(-2);
		abDesbalanceado.insertar(-3);
		abDesbalanceado.insertar(-4);
		

		System.out.println("\n\n\n***********************\n\n\n");
		System.out.println(abDesbalanceado.balanceado());
		
		System.out.println(abDesbalanceado.altura());
		System.out.println(abDesbalanceado.toString());
		System.out.println("\n\n\n");
		System.out.println("\n\n\n***********************\n\n\n");
		System.out.println("\n\n\n***********************\n\n\n");
		System.out.println("\n\n\n***********************\n\n\n");
		abDesbalanceado.rebalancear();
		System.out.println("\n\n\n***********************\n\n\n");
		System.out.println(abDesbalanceado.toString());
		System.out.println(abDesbalanceado.balanceado());
	//	System.out.print(abDesbalanceado.iesimo(4));
		
		
		

	}

}
