package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

	public abstract class DatabaseTournament extends SqlTournament {
	
		private static Connection con = null;
		private static Statement s;

		public static void select() throws SQLException {
		
			boolean success = true;
			
			try {
		
				con = SqlTournament.connectToDB();
				s = con.createStatement();
				ResultSet rs = s.executeQuery ("SELECT * FROM album_test");
		
				while (rs.next()) {
					System.out.println ("Album ID: " + rs.getInt (1)+", Nombre: "+rs.getString(2)+
							", Año: "+rs.getInt(3)+ ", Artista: "+rs.getString(4));
				}
		
			} catch (SQLException e) {
				System.out.println("EXCEPCION DE SQL");
				success = false;
			}
		
			s.close(); con.close();
		
			if (success) {System.out.println("Succesfully Selected");}
		
		}
		/*Integer id_team, String team_name, int total_points, int total_goals, int total_goals_counter, int position*/
		public static void insertTable(int i) throws SQLException{
			
			
			try {
				
				con = SqlTournament.connectToDB();
				s = con.createStatement();
				
				String tableQuery =
						
						"CREATE TABLE `tournament"+i+"` ("+
								  "`id_team` int NOT NULL AUTO_INCREMENT,"+
								  "`team_name` varchar(45) NOT NULL,"+
								  "`total_points` int DEFAULT NULL,"+
								  "`total_goals` int DEFAULT NULL,"+
								  "`total_goals_counter` int DEFAULT NULL,"+
								  "`total_goals_dif` int DEFAULT NULL,"+
								  "`position` int DEFAULT NULL,"+
								  "PRIMARY KEY (`id_team`),"+
								 "UNIQUE KEY `id_team_UNIQUE` (`id_team`)"+
								") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci";
				
				s.executeUpdate(tableQuery);
				
				}catch (SQLException e) {
					System.out.println("EXCEPCION DE SQL");
				}
			s.close(); con.close();
		}
		
		public static boolean checkTable(int i) throws SQLException{
			
			boolean exists = false;
			
			try {
				
				con = SqlTournament.connectToDB();
				s = con.createStatement();
				
				String tableQuery = "SHOW TABLES LIKE 'tournament"+i+"';";
				
				ResultSet rs = s.executeQuery(tableQuery);
				
				if(rs.next()) {
					exists = true; /*O sea, si hay una tabla así, existe*/
				}
				
				} catch (SQLException e) {
					
					System.out.println("EXCEPCION DE SQL");
				}
			
			
			
			s.close(); con.close();
			
			return exists;
		}
		
public static boolean checkFechaTable(int i) throws SQLException{
			
			boolean exists = false;
			
			try {
				
				con = SqlTournament.connectToDB();
				s = con.createStatement();
				
				String tableQuery = "SHOW TABLES LIKE 'fechas_of_tournament"+i+"';";
				
				ResultSet rs = s.executeQuery(tableQuery);
				
				if(rs.next()) {
					exists = true; /*O sea, si hay una tabla así, existe*/
				}
				
				} catch (SQLException e) {
					
					System.out.println("EXCEPCION DE SQL");
				}
			
			
			
			s.close(); con.close();
			
			return exists;
		}
		
		public static void dropTable(int i) throws SQLException{
			
			try {
				
				con = SqlTournament.connectToDB();
				s = con.createStatement();
				String myTableName = "DROP TABLE IF EXISTS `tournament"+i+"`;";
				
				s.executeUpdate(myTableName);
				
				}catch (SQLException e) {
					System.out.println("EXCEPCION DE SQL");
				}
			s.close(); con.close();
		}

		public static void saveTournament(int i, String[] teaminfo) throws SQLException{
			
			String name = teaminfo[0];
			int points =  Integer.parseInt(teaminfo[1]);
			int goals = Integer.parseInt(teaminfo[2]);
			int goalsC = Integer.parseInt(teaminfo[3]);
			int goalsDif = Integer.parseInt(teaminfo[4]);
			int position = Integer.parseInt(teaminfo[5]);
			
			try {
				
				con = SqlTournament.connectToDB();
				s = con.createStatement();
				
				String query = "INSERT INTO tournament"+i+" VALUES (NULL,'"+name+"',"
						+ ""+points+","+goals+","+goalsC+","+goalsDif+", "+position+")";
				
				s.executeUpdate(query);
				
				}catch (SQLException e) {
					System.out.println("EXCEPCION DE SQL");
				}
			s.close(); con.close();
		}
		
		
		public static void insertTableFechas(int i) throws SQLException {
			
			String tableQuery = "CREATE TABLE `fechas_of_tournament"+i+"` ("
					+"  `match_id` int NOT NULL AUTO_INCREMENT,"
					 +"`team_local` varchar(45) NOT NULL DEFAULT 'equipoLocal',"
					  +"`team_visit` varchar(45) NOT NULL DEFAULT 'equipoVisitante',"
					  +"`local_goals` int DEFAULT NULL,"
					  +"`visit_goals` int DEFAULT NULL,"
					  +"`local_points` int DEFAULT NULL,"
					  +"`visit_points` int DEFAULT NULL,"
					  +"`fecha_number` int DEFAULT NULL,"
					  +"`local_total_points` int DEFAULT NULL,"
					  +"`visit_total_points` int DEFAULT NULL,"
					  +"PRIMARY KEY (`match_id`),"
					  +"UNIQUE KEY `match_id_UNIQUE` (`match_id`)"
					+") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
			try {
				con = SqlTournament.connectToDB();
				s = con.createStatement();
				
				s.executeUpdate(tableQuery);
				
				} catch (SQLException e) {
					System.out.println("EXCEPCION DE SQL");
				}
			s.close(); con.close();
		} 
		
		public static void saveMatch(int i, String[] fecha) throws SQLException {
			
			String local_name = fecha[0];
			String visit_name = fecha[1];			
				
				int local_goals = Integer.parseInt(fecha[2]);
				int visit_goals = Integer.parseInt(fecha[3]);
				int local_points = Integer.parseInt(fecha[4]);
				int visit_points = Integer.parseInt(fecha[5]);
				int fecha_number = Integer.parseInt(fecha[6]);
				int local_tpoints = Integer.parseInt(fecha[7]);
				int visit_tpoints = Integer.parseInt(fecha[8]);
			
			try {
				
				con = SqlTournament.connectToDB();
				s = con.createStatement();
				
				String query = "INSERT INTO fechas_of_tournament"+i+"  VALUES (null,'"+local_name+"', '"+visit_name+"',"+
						local_goals+","+visit_goals+","+local_points+","+visit_points+","+fecha_number+","+
						local_tpoints+","+visit_tpoints+");";
				
				String queryFree = "INSERT INTO fechas_of_tournament"+i+"  VALUES (0,'"+local_name+"','"+visit_name+"',null,null,null,null,"+fecha_number+", "
						+local_tpoints+ ",null);";
				
				if (visit_name != "-LIBRE-") {
				s.executeUpdate(query);} else {
					s.executeUpdate(queryFree);
				}
				
				}catch (SQLException e) {
					System.out.println("EXCEPCION DE SQL");
				}
			s.close(); con.close();
		}
		
		
		public static void saveWholeFecha(int i, String[][] fechaEntera, int arrayMatchs) throws SQLException {
			for(int l = 0; l < arrayMatchs; l++) {
				saveMatch(i, fechaEntera[l]);
			}
		}
		
		
}
