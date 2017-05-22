package unPaquete;

public class Bandera {
	private int retazo = 0;
	private int largo;
	private int ultimoRetazo;
	public Bandera (int largo) {
		this.largo = largo;
		ultimoRetazo = largo;
	}
	public int getRetazo() {
		return retazo;
	}
	public int getLargo() {
		return largo;
	}
	
	public void alargarBandera(int aumento) {
		largo += aumento;
		ultimoRetazo = aumento;
		retazo++;
	}
	public int getUltimoRetazo() {
		return ultimoRetazo;
	}
	
	
}
