
import java.sql.*;


public class Driver {
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet rs = null;

	public void connect() {
		String url = "jdbc:mysql://mysql.stud.ntnu.no/pettejn_tdt4145database";
		String username = "pettejn_tdt4145";
		String password = "pettejn_tdt4145";
		
		try {
			System.out.println(url + "?user=" + username + "&password=" + password);
			// 1. get a connection to a database
			myConn = DriverManager.getConnection(url + "?user=" + username + "&password=" + password);
			System.out.println(url + "?user=" + username + "&password=" + password);
		}
		catch (SQLException ex) {
			System.out.println("SQLExeption: " + ex.getMessage());
		}
	}
	
	public void query() {
		try {
			Statement myStmt = myConn.createStatement();
			String query = "SELECT * FROM ØVELSE";
			if (myStmt.execute(query)) {
				rs = myStmt.getResultSet();
			}
			
			while (rs.next()) {
				System.out.println("Navn: "+rs.getString(1) + "," + "Beskrivelse: " + rs.getString(2));
			}
		}
		catch (SQLException ex) {
			System.out.println("SQLExeption: " + ex.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.connect();
		driver.query();
	}
	
}