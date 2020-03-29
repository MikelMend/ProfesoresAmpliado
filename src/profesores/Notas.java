package profesores;

import java.io.Serializable;

public class Notas implements Serializable{

	public int notas[];
	
	public int[] getNotas() {
		return notas;
	}

	public void setNotas(int[] notas) {
		this.notas = notas;
	}

	Notas(){notas= new int[5];}
}
