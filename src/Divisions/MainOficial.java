package Divisions;
import Teams.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class MainOficial {

	public static void main(String[] args) {
		
		System.out.println();
		Scanner sc = new Scanner(System.in);
		int cantidad;
		int eleccion = 1;
		int ruedas = 1;
		String nombre = "División";
		ArrayList<Team> label = new ArrayList<Team>();
		ArrayList<Team> torneoTeams = new ArrayList<Team>();
		Torneo torneo = new Torneo(torneoTeams.toArray(new Team[torneoTeams.size()]), nombre);

		
		
		do {
			System.out.println("0 Salir");
			System.out.println("1 Sumar equipos");
			System.out.println("2 Eliminar equipos");
			System.out.println("3 Configuración de torneo");
			System.out.println("4 Jugar Torneo");
			System.out.println("5 Algo");
			eleccion = sc.nextInt();

		switch(eleccion) {
		case 1:{
			
			do {
			
		System.out.println("Cuantos equipos desea agregar? (0 para salir)");
			cantidad = sc.nextInt();
		for (int i = 0; i < cantidad; i++) { 
			label.add(new Team(sc.next()));}
		System.out.println(label);
		} while(cantidad != 0);break;

		}
		case 2:{
			
			do {
			System.out.println("Qué equipo desea eliminar? (con números, 0 para salir)");
			System.out.println(label);
			cantidad = sc.nextInt();
			if (cantidad!=0) {
			label.remove(cantidad-1);}
			}while(cantidad != 0);
			break;
		}
		case 3:{ 
			System.out.println("Elija nombre de torneo");
			nombre = sc.next();
			System.out.println("Cuantas ruedas quiere?");
			ruedas = sc.nextInt();
			System.out.println("Qué equipos participan? (0 para terminar)");
			System.out.println(label);
			int agregarEquipo;
			for (int i = 0; i < label.size(); i++) {
				agregarEquipo = sc.nextInt();
				
				if (torneoTeams.contains(label.get(agregarEquipo))) {}else {
				
				if (agregarEquipo !=0) {
				torneoTeams.add(label.get(agregarEquipo-1));
				System.out.println(label);}}
			}break;

			
			
			
			
			}
		
		case 4: {
			torneoTeams.toArray();
			torneo.crearTorneo(ruedas);
			break;
		}
		
		
		
		
		
		default: System.out.println("Default");break;

		}
		}while (eleccion != 0);
		
		
		System.out.println("Fin");
	}
}
