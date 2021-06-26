package Teams;

public class Helpers {
	
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
		System.out.println();
		System.out.println("Division:");
		showDivision(division);
		}


}
