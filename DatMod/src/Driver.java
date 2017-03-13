
import java.sql.*;

public class Driver {

	public static void main(String[] args) {
		String url = "jdbc:mysql//localhost:3306/Trening";
		String username = "student";
		String password = "student";
		
		try {
		// 1. get a connection to a database
		Connection myConn = DriverManager.getConnection(url, username, password);
			
			
		// 2. create a statement
		
		Statement myStmt = myConn.createStatement();
		
		// 3. excecute sql query
		
		String sql = "insert into supplier" + "(sno, sname, status, city)" + "values(104, 'DEF', 13, 'Chicago')";
						
		myStmt.executeUpdate(sql);
		
		ResultSet myRs = myStmt.executeQuery("select * from Supplier");
		
		
		// 4. process the result set
		while (myRs.next()) {
			System.out.println(myRs.getInt("sno") + myRs.getString("sname"));
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
