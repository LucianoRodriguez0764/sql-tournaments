package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class SqlTournament {
	
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/";
	public static final String DB = "tournamentssavings";
	public static final String USER = "root";
		
		public static Connection connectToDB() {
			Connection connection = null;
			
		try {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(URL+DB,USER,"0f99fe6cd935");
		
		if (connection != null) { 			//System.out.println("Establecidiisima loco dale");
			
			/*System.out.println("......................");
			System.out.println("Connection established");
			System.out.println("......................");
			System.out.println(); no quiero que cada que suba un equipo mande esto */ 
			
			}
		
		} catch (Exception e){
			
		   e.printStackTrace();
		
		}
			return connection;	
		}

}