package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDao {
	static Connection con = null;
	public static Statement statement;

	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// con =
			// DriverManager.getConnection("jdbc:mysql://103.38.50.113:8080/streamingApp","root","pmDehankar@1996");
			con = DriverManager.getConnection("jdbc:mysql://103.14.99.198:3306/streamingApp", "root", "9QT2{gZvm[Gx~b");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public static Statement getStatement() {

		try {
			con = ConnectionDao.getConnection();
			System.out.println("Connecting with DataService");
			statement = con.createStatement();

		} catch (SQLException m) {
			System.out.println("==>-" + m);
		}
		return statement;

	}

}