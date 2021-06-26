package Divisions;

import Teams.Team;
import Teams.Torneo;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import DB.DatabaseTournament;

public class Main2 {

	public static void main(String[] args) throws SQLException {
		
		
		
		Team equipo9 = new Team("Banfield");
		Team equipo10 = new Team("Argentinos");
		Team equipo11 = new Team("Newells Old Boys");
		Team equipo12 = new Team("Rosario Central");
		Team equipo13 = new Team("Defensa y Justicia");
		Team equipo14 = new Team("Colón de Santa Fé");
		Team equipo15 = new Team("Talleres de Córdoba");
		Team equipo16 = new Team("Atlético Tucumán");
		
		Team equipo17 = new Team("Huracán");
		Team equipo18 = new Team("Central Córdoba");
		Team equipo19 = new Team("Unión de Santa Fé");
		Team equipo20 = new Team("Aldosivi de MdP");
		Team equipo21 = new Team("Godoy Cruz");
		Team equipo22 = new Team("Arsenal de Sarandí");
		Team equipo23 = new Team("Patronato de Paraná");
		Team equipo24 = new Team("Platense");
		
//		,equipo9,equipo10,
//				equipo11,equipo12,equipo13,equipo14,equipo15,equipo16,equipo17,equipo18,equipo19,equipo20};
		
		Team[] trivial2 = {equipo9,equipo10,equipo11,equipo12,equipo13,equipo14,equipo15,equipo16};
		Team[] trivial3 = {equipo17,equipo18,equipo19,equipo20,equipo21,equipo22,equipo23,equipo24};
		
		
		
		
		Team equipo1 = new Team("River Plate");
		Team equipo2 = new Team("Boca Juniors");
		Team equipo3 = new Team("Independiente");
		Team equipo4 = new Team("Racing");
		Team equipo5 = new Team("San Lorenzo");
		Team equipo6 = new Team("Vélez Sarfield");
		Team equipo7 = new Team("Lanús");
		Team equipo8 = new Team("Estudiantes");
		
		Team[] trivial = {equipo1,equipo2,equipo3,equipo4,equipo5,equipo6,equipo7,equipo8,equipo9,equipo10,
						equipo11,equipo12,equipo13,equipo14,equipo15,equipo16,equipo17,equipo18,equipo19,equipo20};
		
		Torneo torneoTrivial = new Torneo(trivial, "Primera A");
		
		torneoTrivial.crearTorneoSQL(1);
		
//		torneoTrivial.crearTorneo(1);
		
		
		
		
	}
	
	
	
	
	

	
	
	
	
}
