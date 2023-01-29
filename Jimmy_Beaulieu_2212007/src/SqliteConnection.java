import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class SqliteConnection {
	
	public static Connection dbConnection() {
		
		try {			
			Class.forName("org.sqlite.JDBC");
			Connection conn =  DriverManager.getConnection("jdbc:sqlite:Studentdb.db");
			//JOptionPane.showMessageDialog(null, "Database connection successful");
			return conn;
		} 
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Error while trying to connect to the database");
			return null;
		}
	}
	
}
