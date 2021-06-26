package Teams;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Random;

import DB.DatabaseTournament;

public class Torneo {
	
	public Team[] participantes;
	public Team campeon;
	public Team ultimo;
	public String nombre;
	public Random rnd = new Random();
	
	int golesidaa, golesidab, golesvueltaa, golesvueltab;
	
	public Torneo(Team[] participantes, String nombre) {
		this.participantes = participantes;
		this.nombre = nombre;
	}
	
	public void mezclar() {
		int length = participantes.length;
		for (int h = 0; h < 20 /*repeticiones*/; h++) {
		for (int i = 0; i < length; i++) {;
			int aleatorio = (int)(Math.random()*length);
			int aleatorio2 = (int)(Math.random()*length);
			Team comodin = participantes[aleatorio2];
			participantes[aleatorio2] = participantes[aleatorio];
			participantes[aleatorio] = comodin;
			
		}
	}
	}
	
	public void makePosiciones() {
		this.campeon = participantes[0];
		this.ultimo = participantes[(participantes.length)-1];
	}
	
	public void enfrentar(int indexA, int indexB) {
		System.out.println(participantes[indexA].getNombre() + " vs "+ participantes[indexB].getNombre());
	}
	public void enfrentarCG(int indexA, int indexB) {   // Con goles (, amistosos)
		participantes[indexA].makeGoles();participantes[indexB].makeGoles();
		System.out.println(participantes[indexA].getNombre() +" "+ participantes[indexA].getGoles()+":"+
				participantes[indexB].getGoles()+" "+participantes[indexB].getNombre() );
	}
	
	public void enfrentarCGYP(int indexA, int indexB) {   // Con goles y puntos
		participantes[indexA].makeGoles();participantes[indexB].makeGoles();
		int golesA = participantes[indexA].getGoles();
		int golesB = participantes[indexB].getGoles();
		
		participantes[indexA].golesAcumulados += golesA;
		participantes[indexA].golescAcumulados += golesB;
		participantes[indexB].golesAcumulados += golesB;
		participantes[indexB].golescAcumulados += golesA;
		
		// Setea los puntos
		if (golesA > golesB) {participantes[indexA].setPuntos(3); participantes[indexA].setPuntosTotales(3); participantes[indexB].setPuntos(0);}else{
			if(golesB > golesA) {participantes[indexB].setPuntos(3);participantes[indexB].setPuntosTotales(3);participantes[indexA].setPuntos(0);}else{
				 participantes[indexA].setPuntos(1);participantes[indexB].setPuntos(1);
				 participantes[indexA].setPuntosTotales(1);participantes[indexB].setPuntosTotales(1);
			}
		}
		int puntosA = participantes[indexA].getPuntos(); int puntosB = participantes[indexB].getPuntos();
		participantes[indexA].puntosAcumulados += puntosA; participantes[indexB].puntosAcumulados += puntosB;
		
		System.out.print(participantes[indexA].getNombre() +" "+ golesA +":"+
				golesB+" "+participantes[indexB].getNombre() + " (" + puntosA + ")"
						+ "(" + puntosB + ")");
//		Muestra los puntos acumulados automáticamente después del partido
		System.out.println("(" + participantes[indexA].getPuntosAcumulados() + ")"				+ "(" + participantes[indexB].getPuntosAcumulados() + ")");
	}
	
	public String[] enfrentarCGYPforSQL(int indexA, int indexB) { // Con goles y puntos enviando la información
		
		String[] matchInfo = new String[9];
		
		matchInfo[0] = participantes[indexA].getNombre();
		matchInfo[1] = participantes[indexB].getNombre();
		
		participantes[indexA].makeGoles();participantes[indexB].makeGoles();
		int golesA = participantes[indexA].getGoles();
		int golesB = participantes[indexB].getGoles();
		
		matchInfo[2] = Integer.toString(golesA);
		matchInfo[3] = Integer.toString(golesB);
		
		participantes[indexA].golesAcumulados += golesA;
		participantes[indexA].golescAcumulados += golesB;
		participantes[indexB].golesAcumulados += golesB;
		participantes[indexB].golescAcumulados += golesA;
		
		// Setea los puntos
		if (golesA > golesB) {participantes[indexA].setPuntos(3); participantes[indexA].setPuntosTotales(3); participantes[indexB].setPuntos(0);}else{
			if(golesB > golesA) {participantes[indexB].setPuntos(3);participantes[indexB].setPuntosTotales(3);participantes[indexA].setPuntos(0);}else{
				 participantes[indexA].setPuntos(1);participantes[indexB].setPuntos(1);
				 participantes[indexA].setPuntosTotales(1);participantes[indexB].setPuntosTotales(1);
			}
		}
		int puntosA = participantes[indexA].getPuntos(); int puntosB = participantes[indexB].getPuntos();
		participantes[indexA].puntosAcumulados += puntosA; participantes[indexB].puntosAcumulados += puntosB;
		
		matchInfo[4] = Integer.toString(puntosA);
		matchInfo[5] = Integer.toString(puntosB);
		
		matchInfo[7] = Integer.toString(participantes[indexA].puntosAcumulados);
		matchInfo[8] = Integer.toString(participantes[indexB].puntosAcumulados);
		
		System.out.print(participantes[indexA].getNombre() +" "+ golesA +":"+
				golesB+" "+participantes[indexB].getNombre() + " (" + puntosA + ")"
						+ "(" + puntosB + ")");
//		Muestra los puntos acumulados automáticamente después del partido
		System.out.println("(" + participantes[indexA].getPuntosAcumulados() + ")"				+ "(" + participantes[indexB].getPuntosAcumulados() + ")");
	
	
		return matchInfo;
	
	}
	
	
	public void crearTorneoSQL(int ruedas) throws SQLException {
		
		mezclar();
		
		int length = participantes.length;
		int fechascant = (length-1);
		int torneoNumber = 1;
		String[][] fechaAux;
		int arrayMatchs = 0;
		
		if (participantes.length%2 == 0) { arrayMatchs = ((participantes.length)/2);}
		else {arrayMatchs = ((participantes.length+1)/2);}
		
		while(DatabaseTournament.checkFechaTable(torneoNumber)) {
			
			torneoNumber++;
			
			while(DatabaseTournament.checkTable(torneoNumber)) {
				torneoNumber++;
			}
		}
		
		DatabaseTournament.insertTableFechas(torneoNumber);
		
		for (int j = 0; j < (ruedas); j++) {
		for (int i = 0; i < fechascant; i++) {
			fechaAux = crearFechaSQL(i,j);
			DatabaseTournament.saveWholeFecha(torneoNumber, fechaAux, arrayMatchs);
		}}
		
		for(int i=0; i<length; i++) {
			participantes[i].setIndice(i);
		participantes[i].setGolesDifAcumulados(participantes[i].getGolesAcumulados() - participantes[i].getGolescAcumulados());
		}
		
		Arrays.sort(participantes);
		
		makePosiciones();
		showDivision();
		cantarPosiciones();
		
		DatabaseTournament.insertTable(torneoNumber);
		
		for (int i = 0; i < participantes.length; i++) {
			String[] teaminfo = Torneo.getAllTeamInfo(participantes[i]);
			DatabaseTournament.saveTournament(torneoNumber, teaminfo);
		}	
		
		resetearPuntos();
		resetearGolesAcumulados();
				
	}
	
	public void crearTorneo(int ruedas) {
		
		mezclar();
		
		int length = participantes.length;
		int fechascant = (length-1);
		
		for (int j = 0; j < (ruedas); j++) {
		for (int i = 0; i < fechascant; i++) {
			crearFecha(i,j);
		}}
		
		for(int i=0; i<length; i++) {
			participantes[i].setIndice(i);
		participantes[i].setGolesDifAcumulados(participantes[i].getGolesAcumulados() - participantes[i].getGolescAcumulados());
		}
		
		Arrays.sort(participantes);
		
		makePosiciones();
		showDivision();
		cantarPosiciones();
		
//		Los saco momentáneamente ya que no me sirve si no lo puedo guardar
		resetearPuntos();
		resetearGolesAcumulados();
		
	}
	
	public void showDivision() {
		int length = participantes.length;
		System.out.println("-----------------------------------------");
		System.out.println("Tabla de posiciones de " + this.nombre);
		System.out.println("-----------------------------------------");
		for(int i=0; i<length; i++) {
			participantes[i].setIndice(i);
			System.out.println(participantes[i]);
		}
	}
	public void cantarPosiciones() {
		System.out.println("*****************************************");
		System.out.println("El campeón fue " + this.campeon.getNombre());
		System.out.println("El último fue " + this.ultimo.getNombre());	
		System.out.println("*****************************************");
	}
	public void resetearPuntos() {
		int length = participantes.length;
		for(int i=0; i<length; i++) {
			participantes[i].setPuntosAcumulados(0);}
	}
	public void resetearGolesAcumulados(){
		int length = participantes.length;
		for(int i=0; i<length; i++) {
			participantes[i].setGolesAcumulados(0);
			participantes[i].setGolescAcumulados(0);}
	}
	
	
	/*********************************************************/
	
	//COPAS
	
	public void crearCopa() {
		
		
		
	}
	
	public void makeCruce(int a, int b, int idayvuelta) {
		
		enfrentarCGYP(a, b);
		
		this.golesidaa = participantes[a].goles;
		this.golesidab = participantes[b].goles;
		
		
		if(idayvuelta == 1) {
			enfrentarCGYP(b, a);
			
			this.golesvueltaa = participantes[a].goles;
			this.golesvueltab = participantes[b].goles;
			
		}
		
		definirCruce(a,b, idayvuelta);
	}
		
	public void definirCruce(int a, int b, int idayvuelta) {
		
		Team winner = null;
		
		int golesparaA, golesparaB;
		
		if (participantes[a].puntosAcumulados > participantes[b].puntosAcumulados) {
			winner = participantes[a];
		}else
		if (participantes[a].puntosAcumulados < participantes[b].puntosAcumulados) {
			winner = participantes[b];
		}else
		if (participantes[a].golesAcumulados > participantes[b].golesAcumulados) {
			winner = participantes[a];
		}else
		if (participantes[a].golesAcumulados < participantes[b].golesAcumulados) {
			winner = participantes[b];
		}else
			
		if (idayvuelta==1) {
			golesparaA = (2*golesvueltaa + golesidaa);
			golesparaB = (2*golesidab + golesvueltab);
			
			if (golesparaA > golesparaB) {
				winner = participantes[a];
			}else
			if (golesparaA < golesparaB) {
				winner = participantes[b];
			}else {
				tandaDePenales(a,b);
				winner = participantes[a]; System.out.println("Decision de penales");
			}
			
		}
		
		System.out.println("El ganador es " + winner.nombre);
	}
	
	
	
	public void tandaDePenales(int a, int b) {
		
		int chancesDeFalloA=0;
		int chancesDeFalloB=0;
		int penalesA=0, penalesB=0,penalesRA=5, penalesRB=5, tanda=0;
		String penalesAG = "", penalesBG = "";
		String winnerPenales = null;
		
		int stop = 0;
		
		do {
			
		tanda++;
		if (tanda > 5) {stop = 3;}
			
		chancesDeFalloA = this.rnd.nextInt(10);
		chancesDeFalloB = this.rnd.nextInt(10);
		
		if(penalesRA > 0 && stop == 0) {
		if (chancesDeFalloA < 3) {
			
			penalesAG += "X ";}else {penalesAG += "O "; penalesA++;}
		
		penalesRA--;}
		
		stop = checkWinPenales(penalesRA,penalesRB, penalesA, penalesB);
		
		if(penalesRA > 0 && stop == 0) {
		if (chancesDeFalloB < 3) {
			
			penalesBG += "X ";}else {penalesBG += "O "; penalesB++;}
		
		penalesRB--;}
		
		stop = checkWinPenales(penalesRA,penalesRB, penalesA, penalesB);
		
		if (stop == 4) {stop = 0; tanda = 4;}
		
		} while(stop == 0);
		
		System.out.println(penalesAG);
		System.out.println(penalesBG);
		if (stop == 1) {winnerPenales = participantes[a].nombre;}else
			if (stop == 2) {winnerPenales = participantes[b].nombre;}else
				if (stop == 3) {winnerPenales = "Empate de penalessss";}
		
				else {System.out.println("kaka");}
		System.out.println("El ganador es " + winnerPenales);
		

	}

	
	private int checkWinPenales(int PRA,int PRB, int PA, int PB) {
			if(PRB < (PA-PB)) {return 1;}
			if(PRA < (PB-PA)) {return 2;}
			if(PRA == 0 && PRB == 0 && PA == PB) {return 4;}
			else return 0;
	}

	/*********************************************************/
	
	
// Insertar texto del final
	public void crearFecha(int fecha, int rueda) {
		int length = participantes.length;
		int fechascant = (length-1);
		System.out.println("");
		System.out.println("Fecha " + ((fecha+1)+(rueda*fechascant)) + ":");	
		
		int j, k, divisor;
		if (fecha%2 == 0) {divisor = (fecha/2) + 1;}else{divisor = (fecha+1)/2 + 1;}
		if (length%2 == 0) {k = (fecha + length);}else{k = (fecha + length + 1);} 
		
		j = (fecha + 1);
		
		if (fecha>length) {
			
			System.out.println("Cantidad errónea de fechas");} else {
			
			for (int i = 0; i < divisor; i++) {
				
				if (participantes[i].isOccuped()) {;} else {
				if (i == (j-i)) {
					if (length%2 == 0) {
					enfrentarCGYP(i, (length-1));
					participantes[i].setOccuped(true);
					participantes[length-1].setOccuped(true);} else {
						participantes[i].setOccuped(true);
						System.out.println("Equipo libre: "+participantes[i].getNombre() +" ("+ participantes[i].getPuntosAcumulados()+")");
					}
				} else {   // Si juega 3 vs 3 (el vs el), que juege contra el último ;)
					enfrentarCGYP(i, (j-i));
					participantes[i].setOccuped(true);
					participantes[j-i].setOccuped(true);}
					}
				}
			
			int variable = divisor;
			
			for (; variable < length; variable++) {
				if (participantes[variable].isOccuped()) {;} else {
					if(variable == (k-variable)) {
						if (length%2 == 0) {
						enfrentarCGYP(variable, (length-1));
						participantes[variable].setOccuped(true);
						participantes[length-1].setOccuped(true);} else {
							participantes[variable].setOccuped(true);
							System.out.println("Equipo libre: "+participantes[variable].getNombre() +" (" + participantes[variable].getPuntosAcumulados()+")");}
						} else {
					enfrentarCGYP(variable, (k-variable)); participantes[variable].setOccuped(true);participantes[k-variable].setOccuped(true);}
				}
			}
		}
		
		for (int i = 0; i < length; i++) {
			participantes[i].setOccuped(false);}
	}
	
	public String[][] crearFechaSQL(int fecha, int rueda) {
		
		int arrayMatchs = 0;
		int matchCounter = 0;
		
		if(participantes.length % 2 == 0) {arrayMatchs = (participantes.length/2);}else {arrayMatchs = ((participantes.length+1)/2);}
		
		String[][] fechaInfo = new String[arrayMatchs][9];
		String[] libre_info = new String[9];
		
		int length = participantes.length;
		int fechascant = (length-1);
		System.out.println("");
		System.out.println("Fecha " + ((fecha+1)+(rueda*fechascant)) + ":");	
		
		int j, k, divisor;
		if (fecha%2 == 0) {divisor = (fecha/2) + 1;}else{divisor = (fecha+1)/2 + 1;}
		if (length%2 == 0) {k = (fecha + length);}else{k = (fecha + length + 1);} 
		
		j = (fecha + 1);
		
		if (fecha>length) {
			
			System.out.println("Cantidad errónea de fechas");} else {
			
			for (int i = 0; i < divisor; i++) {
				
				if (participantes[i].isOccuped()) {;} else {
				if (i == (j-i)) {
					if (length%2 == 0) {
					fechaInfo[matchCounter] = enfrentarCGYPforSQL(i, (length-1)); matchCounter++;
					participantes[i].setOccuped(true);
					participantes[length-1].setOccuped(true);} else {
						participantes[i].setOccuped(true);
						System.out.println("Equipo libre: "+participantes[i].getNombre() +" ("+ participantes[i].getPuntosAcumulados()+")");
						
						
						libre_info[0] = participantes[i].getNombre(); 
						libre_info[1] = "-LIBRE-";
						libre_info[2] = "-1";
						libre_info[3] = "-1";
						libre_info[4] = "-1";
						libre_info[5] = "-1";
						libre_info[7] = Integer.toString(participantes[i].getPuntosAcumulados());
						libre_info[8] = "-1";
						
						//Setea la info del equipo libre (fucking error)
						for (int z = 0; z<9; z++) {
							fechaInfo[arrayMatchs-1][z] = libre_info[z];
						}				
					}
				} else {   // Si juega 3 vs 3 (el vs el), que juege contra el último ;)
					fechaInfo[matchCounter] = enfrentarCGYPforSQL(i, (j-i)); matchCounter++;
					participantes[i].setOccuped(true);
					participantes[j-i].setOccuped(true);}
					}
				}
			
			int variable = divisor;
			
			for (; variable < length; variable++) {
				if (participantes[variable].isOccuped()) {;} else {
					if(variable == (k-variable)) {
						if (length%2 == 0) {
						fechaInfo[matchCounter]	= enfrentarCGYPforSQL(variable, (length-1)); matchCounter++ ;
						participantes[variable].setOccuped(true);
						participantes[length-1].setOccuped(true); } else {
						participantes[variable].setOccuped(true);
						System.out.println("Equipo libre: "+participantes[variable].getNombre() +" (" + participantes[variable].getPuntosAcumulados()+")");
						
						libre_info[0] = participantes[variable].getNombre(); 
						libre_info[1] = "-LIBRE-";
						libre_info[2] = "-1";
						libre_info[3] = "-1";
						libre_info[4] = "-1";
						libre_info[5] = "-1";
						libre_info[7] = Integer.toString(participantes[variable].getPuntosAcumulados());
						libre_info[8] = "-1";
						
//						Setea la info del equipo libre (fucking error)
						for (int z = 0; z<9; z++) {
							fechaInfo[arrayMatchs-1][z] = libre_info[z];
								}
							}				
						} else {
					fechaInfo[matchCounter] = enfrentarCGYPforSQL(variable, (k-variable));
					matchCounter++;
					participantes[variable].setOccuped(true);
					participantes[k-variable].setOccuped(true);}
				}
			}
		}
		
		for (int i = 0; i < length; i++) {
			participantes[i].setOccuped(false);}
		
		// Completa el campo 6 "Número de fecha"
		for (int i = 0; i< arrayMatchs; i++) {
			fechaInfo[i][6] = Integer.toString((fecha+1)+(rueda*fechascant));
		}
		
		return fechaInfo;
		
	}
	
	
	// Intercambiar de ligas dos equipos x
	public void intercambiarEquipos(Team descenso, Torneo torneito, Team ascenso) {
		Team intercambio = descenso;
		participantes[descenso.getIndice()] = ascenso;
		torneito.participantes[ascenso.getIndice()] = intercambio;
		
		System.out.println( intercambio.getNombre() +"<-->"+ ascenso.getNombre());
	}
	// Intercambiar según último del this / campeón del argumento
	public void intercambiarDesascensos(Torneo torneito) {
		Team descenso = this.ultimo;
		Team ascenso = torneito.campeon;
		Team intercambio = descenso;
		participantes[descenso.getIndice()] = ascenso;
		torneito.participantes[ascenso.getIndice()] = intercambio;
		System.out.println();
		System.out.println(intercambio.getNombre() + "(" + this.nombre +") Descendió"); 
		System.out.println(ascenso.getNombre() +"(" + torneito.nombre +") Ascendió");
		System.out.println();
	}
	
	// Para el transpaso de datos de SQL
	public static String[] getAllTeamInfo(Team t) {
		
		String[] info = new String[6];
		
		info[0] = t.getNombre();
		info[1] = Integer.toString(t.puntosAcumulados);
		info[2] = Integer.toString(t.golesAcumulados);
		info[3] = Integer.toString(t.golescAcumulados);
		info[4] = Integer.toString(t.golesDifAcumulados);
		info[5] = Integer.toString(t.indice+1);
		
		return info; 
		
	}
	
}

// Texto guía para "crearFecha(int fecha)"
// Basicamente se divide en tres números importantes: divisor es el que señala en una fecha, hasta que indice de equipo
// Hay que enfrentarlos via "0vs1" - "0vs2" y "1vsUlt" - "0vs3" y "1vs2" ... es decir, el grupo que se va agrandando.
// Este grupo aumenta el índice en un rango de 1 2 2 3 3 4 4 5 5 (las últimas fechas notaremos que siempre los enfrentamientos
// se realizan con este método por que ya no quedan equipos debajo del índice)
// Este primer grupo arriba del divisor, usa J, una variable de enfrentamiento, siempre este grupo enfrenta a rivales tales que
// su indice + indice rival es igual a fecha+1 (fecha empezando de cero):: Ejemplo: 1vs5 se enfrentan en la fecha 6-1.  
// recordar que el primer equipo es 0, tomo como referencia un torneo de 8 de length (cant de equipos)
// El segundo grupo utiliza k, que es basicamente el indice de enfrentamiento tal que indice+indicerRival = length+1
// , entonces simplemente se enfrentan vs esos equipos (Ejemplos en fecha 1 o la segunda, k = 8 + 1, o sea que el equipo 4 que)
// está debajo del divisor, enfrentaría al 5.
// Luego los occuped hacen que no hayan repetidos, al final de la fecha se setean como false para repetir fechas y hacer un
// torneo completo. También el gran problema de cuando te enfrentás a vos mismo se soluciona en ambos grupos:
// Si se da que tu indice de enfrentamiento es igual al tuyo, entonces enfrentas al length-1, o sea al último equipo que
// es el que siempre varía.
// NUEVO: Le agregué las combinaciones para equipos impares. Qué hice? Bueno. después de errores pelotudos, lo único que cambia
// es que ahora en vez de enfrentar al último si su indice = indice de enfrent, solo se ponen como ocupados (ejemplo con 5 equipos:)
// (0vs1 2vs4 3vs5? NO ocupado pero sin enfrentamiento), esto está como condicional aparte en cada grupo, "Si son iguales.. si
// Son pares hace esto, sino, solo marcalo como ocupado", además ajusté que K se le sume 1 si es que el length es impar (así no
// varía el indice de enfrentamiento) Y gg! Funciona.




// FALTA AGREGAR: Goles, desempates, tabla o fechas con más info, + - y GEP
// partidos desempate, penales, ascenso y descenso entre dos o más divisiones
// Menú bien hecho para mejora de interfaz
