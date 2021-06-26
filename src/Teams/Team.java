package Teams;
import java.util.Random;

public class Team implements Comparable<Team> {
	
	public String nombre;
	public int indice = -2; //Valor por defecto tiene que ser != a 0, -1 es que no pertenece a esa división y (0,length-1) el indice real
	public int tabla;
	public int goles;
	public int puntos;
	public int puntosAcumulados;
	public int puntosTotales;
	public int golesAcumulados;
	public int golescAcumulados;
	public int golesDifAcumulados;
	public int campeonatos;
	public Random rnd = new Random();
	public boolean occuped = false;
	public int desempate=0;
	
	///////////////////////////////////////////
	
	
	public Team(String nombre) {
		this.nombre = nombre;
	}

	
	public String getNombre() {
		return this.nombre;
	}
	public int getGoles() {
		return this.goles;
	}
	public void setGoles(int goles) {
		this.goles = goles;
	}
	public int getGolesAcumulados() {
		return this.golesAcumulados;
	}
	public void setGolesAcumulados(int golesa) {
		this.golesAcumulados = golesa;
	}
	public int getGolescAcumulados() {
		return this.golescAcumulados;
	}
	public void setGolescAcumulados(int golesc) {
		this.golescAcumulados = golesc;
	}
	public int getGolesDifAcumulados() {
		return this.golesDifAcumulados;
	}
	public void setGolesDifAcumulados(int golesd) {
		this.golesDifAcumulados = golesd;
	}
	public int getIndice() {
		return this.indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	public int getPuntos() {
		return this.puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public int getPuntosTotales() {
		return this.puntosTotales;
	}
	public void setPuntosTotales(int puntost) {
		this.puntosTotales = puntost;
	}
	public int getPuntosAcumulados() {
		return this.puntosAcumulados;
	}
	public void setPuntosAcumulados(int puntosAcumulados) {
		this.puntosAcumulados = puntosAcumulados;
	}
	public boolean isOccuped() {
		return this.occuped;
	}
	public void setOccuped(boolean occuped) {
		this.occuped = occuped;
	}
	
	///////////////////////////////////////////
	
	public void darIndice(Team[] division) {
		for (int i = 0; i < division.length; i++) {
			if (this.nombre == division[i].nombre) {
				this.indice = i;break;
			}else{this.indice = -1;}
		}	
	}
	
	static public void cambiarIndice(Team descendido, Team ascendido) {
		int momentaneo = descendido.getIndice();
		descendido.setIndice(ascendido.getIndice());
		ascendido.setIndice(momentaneo);
	}
	
	public void makeGoles() {
		int dado = rnd.nextInt(12);
		switch(dado){
		case 0: this.goles = 1; break;
		case 1: this.goles = 1; break;
		case 2: this.goles = 2; break;
		case 3: this.goles = 2; break;
		case 4: this.goles = 3; break;
		case 5: this.goles = 4; break;
		case 6: this.goles = 0; break;
		case 7: this.goles = 0; break;
		case 8: this.goles = 0; break;
		case 9: this.goles = 0; break;
		case 10: this.goles = 1; break;
		case 11: this.goles = 1; break;
		}
	}
	
	public void enfrentar(Team o) {   // Con goles (, amistosos)
			makeGoles();o.makeGoles();
			System.out.println(this.getNombre() +" "+ this.getGoles()+":"+
					o.getGoles()+" "+o.getNombre() );
			if (this.goles > o.goles) {this.desempate = 1; o.desempate = 0;}
			else if (this.goles < o.goles) {o.desempate = 1; this.desempate = 0;}
			else if (this.goles == o.goles) {enfrentar(o);}
	}
	
	
	
	@Override
	public String toString() {
		return this.puntosAcumulados +" puntos, "+ this.nombre + /*" i: " + this.indice */
				" GF/GC: " + this.golesAcumulados + "-" + this.golescAcumulados + " (" + this.golesDifAcumulados + ")";
	}
//	@Override
//	public String toString() {
//		return this.nombre;
//	}
	@Override
	public int compareTo(Team o) {
		if (this.puntosAcumulados > o.puntosAcumulados) {return -1;}else{
		if (this.puntosAcumulados < o.puntosAcumulados) {return 1;}else{
			if (this.golesDifAcumulados > o.golesDifAcumulados) {return -1;}else{
				if (this.golesDifAcumulados < o.golesDifAcumulados) {return 1;}else{
			if (this.golesAcumulados > o.golesAcumulados){return -1;}else{
				if (this.golesAcumulados < o.golesAcumulados){return 1;}else{
					enfrentar(o); if (this.desempate == 1) {return -1;}else{
						return 1;
					}
				}
				
			}
		}}}}
	}
	
}
