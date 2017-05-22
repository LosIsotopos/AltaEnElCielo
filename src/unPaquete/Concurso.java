package unPaquete;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Concurso {
	private Bandera[] escuelas;
	private int rollo;
	private String path;
	private int masLargo;
	private int masRetazos;
	private int[] mismoRetazo = new int[3]; 
	public Concurso(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path+".in"));
		this.path = path;
		rollo = sc.nextInt();
		escuelas = new Bandera[sc.nextInt()];
		for (int i = 0; i < escuelas.length; i++) {
			escuelas[i] = new Bandera(sc.nextInt());
			rollo -= escuelas[i].getLargo();
		}
		sc.close();
	}
	
	public void resolver() {
		int rolloAnterior = -1;
		int aCortar = 0;
		
		while(rollo > 0 && rolloAnterior != rollo) {
			rolloAnterior = rollo;
			for (int i = 0; i < escuelas.length; i++) {
				aCortar = cortarRollo(escuelas[i].getUltimoRetazo());
				if(puedoCortar(aCortar)) {
					rollo -= aCortar;
					escuelas[i].alargarBandera(aCortar);
				}
				
			}
		}
		masLargo = buscarMasLargo();
		masRetazos = buscarMasRetazos();
		hayRetazosIguales();
	}
	
	private int buscarMasLargo() {
		int maximo = -1;
		int indice = -1;
		for (int i = 0; i < escuelas.length; i++) {
			if (maximo < escuelas[i].getLargo()) {
				maximo = escuelas[i].getLargo();
				indice = i;
			}
		}
		return indice;
	}
	
	private int buscarMasRetazos() {
		int maximo = -1;
		for (int i = 0; i < escuelas.length; i++) {
			if (maximo < escuelas[i].getRetazo()) {
				maximo = escuelas[i].getRetazo();
			}
		}
		return maximo;
	}
	 private void hayRetazosIguales() {
		 int maximo = -1;
		 int j;
		 for (int i = 0; i < escuelas.length; i++) {
			 j = i + 1;
			 while ( j < escuelas.length && escuelas[i].getRetazo() != escuelas[j].getRetazo()) {
				 j++;
			 }
			 if (j < escuelas.length && maximo < escuelas[i].getRetazo()) {
				 maximo = escuelas[i].getRetazo();
				 mismoRetazo[0] = maximo;
				 mismoRetazo[1] = i+1;
				 mismoRetazo[2] = j+1;
			 }
		 }
	 }
	

	private boolean puedoCortar(int aCortar) {
			return aCortar <= rollo;
	}

	private int cortarRollo(int retazoAnterior) {
		int nuevoRetazo = retazoAnterior;
		while(retazoAnterior > 0) {
			nuevoRetazo += retazoAnterior % 10;
			retazoAnterior /= 10;
		}
		return nuevoRetazo;
	}
	
	public void mostrarSolucion() {
		System.out.println(masLargo+1 + " " + escuelas[masLargo].getLargo());
		System.out.println(masRetazos);
		System.out.println(rollo);
		for (int i = 0; i < mismoRetazo.length; i++) {
			System.out.print(mismoRetazo[i] + " ");
		}
		
	}

	public void imprimirSolucion() throws IOException {
		PrintWriter pr = new PrintWriter(new FileWriter(path + ".out"));
		pr.println(masLargo + 1 + " " + escuelas[masLargo].getLargo());
		pr.println(masRetazos);
		pr.println(rollo);
		for (int i = 0; i < mismoRetazo.length; i++) {
			pr.print(mismoRetazo[i] + " ");
		}
		pr.close();
	}
}
