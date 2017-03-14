
import java.sql.*;
import java.util.Scanner;

public class Driver {
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet rs = null;

	public void connect() {
		String url = "jdbc:mysql://mysql.stud.ntnu.no/pettejn_tdt4145database";
		String username = "pettejn_tdt4145";
		String password = "pettejn_tdt4145";
		
		try {
			// 1. get a connection to a database
			myConn = DriverManager.getConnection(url + "?user=" + username + "&password=" + password);
		}
		catch (SQLException ex) {
			System.out.println("SQLExeption: " + ex.getMessage());
		}
	}
	
	public void excercises() {
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
	
	public void getOrderedExcercises() {
		try {
			Statement myStmt = myConn.createStatement();
			System.out.println("Hva vil du sortere etter? (Prestasjon, Personlig form, Dato)");
			Scanner scanner = new Scanner(System.in);
			String query = null;
			String choice = scanner.nextLine();
			if (choice.equals("Prestasjon")) {
				query = "SELECT * FROM TRENINGSØKT\n ORDER BY PRESTASJON";
			}
			else if(choice.equals("Dato")) {
				query = "SELECT * FROM TRENINGSØKT\n ORDER BY DATO";
			}
			else if(choice.equals("Personlig form")) {
				query = "SELECT * FROM TRENINGSØKT\n ORDER BY PERSONLIGFORM";
			}
			
			scanner.close();
			if (myStmt.execute(query)) {
				rs = myStmt.getResultSet();
			}
			
			System.out.println("ØktID   Dato         Starttidspunkt   Varighet   Personlig form   Prestasjon    Notat");
			while (rs.next()) {
				System.out.println(rs.getString(1) + "       " +  rs.getString(2) + "   " +  rs.getString(3) + "         " + 
			rs.getString(4) + "          " +  rs.getString(5) + "                " +  rs.getString(6) + "             " +  rs.getString(7));
				
			}
		}
		catch (SQLException ex) {
			System.out.println("SQLExeption: " + ex.getMessage());
		}
	}
	
	public void insert() {
		try {
			System.out.println("Skriv tabellnavn: ");
			Scanner scanner = new Scanner(System.in);
			String dbname = scanner.nextLine();
			
			System.out.println("Skriv atributtene du ønsker å legge til, deretter verdiene");
			String attributes = scanner.nextLine();
			
			while (scanner.hasNext()) {
				String input = "INSERT INTO " + dbname + " (";
				input += attributes + ") VALUES (" + scanner.nextLine() +");";
				myStmt = myConn.createStatement();
				myStmt.executeUpdate(input);
				input = null;
			}
			
			scanner.close();
		}
		
		catch (SQLException ex) {
			System.out.println("SQLExeption: " + ex.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.connect();
		System.out.println("Hva vil du gjøre?\n\n1. Se øvelser\n2. Legge til i databasen\n3. Se oversikt over treningsøkter");
		Scanner scanner = new Scanner(System.in);
		String choice = scanner.nextLine();
		if (choice.equals("1")) {
		driver.excercises();
		}
		else if (choice.equals("2")) {
		driver.insert();
		}
		else if (choice.equals("3")) {
		driver.getOrderedExcercises();
		}
		scanner.close();
	}
	
}