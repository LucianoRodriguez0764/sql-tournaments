package Divisions;

import Teams.Team;

public class Main {

	public static void main(String[] args) {
		
		Team equipo1 = new Team("River Plate");
		Team equipo2 = new Team("Boca Juniors");
		Team equipo3 = new Team("Independiente");
		Team equipo4 = new Team("Racing");
		Team equipo5 = new Team("San Lorenzo");
		Team equipo6 = new Team("Vélez Sarfield");
		Team equipo7 = new Team("Lanús");
		Team equipo8 = new Team("Colón");
		Team equipo9 = new Team("Banfield");
		Team equipo10 = new Team("Estudiantes");
		Team equipo11 = new Team("Argentinos");
		Team equipo12 = new Team("Newell's");
		
		
		//división trivial
		
		Team[] trivial = {equipo1,equipo2,equipo3,equipo4};
		darIndices(trivial);
		
		
		// Creamos las dos divisiones.
		Team[] primera = {equipo1,equipo2,equipo3,equipo4,equipo5,equipo6};
		Team[] segunda = {equipo7,equipo8,equipo9,equipo10,equipo11,equipo12};
		// Damos índices a cada equipo de ambas.		
		int plength = primera.length;
		int slength = segunda.length;
		darIndices(primera); darIndices(segunda);
		
		showDivisiones(primera, segunda);
		
		// Para intercambiar dos equipos, se les asigna el valor de descenso -1 y de ascenso 1, y se llama a desascender.
		
		//Métodos borrados
//		equipo2.setDesascenso(-1);
//		equipo12.setDesascenso(1);
//		Team.desascender(primera, segunda);
		
		showDivisiones(primera, segunda);		
	}
	
	
	
	
	
	
	
	
	static public void showDivision(Team[] division) {
		int dlength = division.length;
		for(int i=0; i<dlength; i++) {
			System.out.println(division[i]);}
	}
	static public void darIndices(Team[] division) {
		int dlength = division.length;
		for(int i=0; i<dlength; i++) {
			division[i].darIndice(division);}
	}
	static public void showDivisiones(Team[] primera, Team[] segunda) {
	System.out.println("------------------");
	System.out.println("Primera:");
	System.out.println();
	showDivision(primera);
	System.out.println("------------------");
	System.out.println("Segunda:");
	System.out.println();
	showDivision(segunda);
	System.out.println();
	}
	static public void showTrivial(Team[] division) {
		System.out.println("------------------");
		System.out.println("Division:");
		System.out.println();
		showDivision(division);
		}

	
	
	
	
}
