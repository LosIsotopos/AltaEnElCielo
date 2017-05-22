package unPaquete;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Concurso c1 = new Concurso("04");
		c1.resolver();
		c1.mostrarSolucion();
		c1.imprimirSolucion();

	}

}
