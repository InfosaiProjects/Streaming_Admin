package dao;

import bean.LoginPojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class LoginDao {
	int no = 0;
	public static Statement statement;
	public static ResultSet rs;
	static PreparedStatement pst;
	static PreparedStatement ps;
	public static Connection con;

	public LoginDao() throws ClassNotFoundException {
		try {
			con = ConnectionDao.getConnection();
			System.out.println("Connecting with DataService");
			statement = con.createStatement();

		} catch (SQLException m) {
			System.out.println("==>-" + m);
		}
	}

	public Object[] validate(LoginPojo loginpojo) throws ClassNotFoundException {
		boolean status = false;

		Object[] object = new Object[4];
		Object adminId = null;

		try {
			Connection connection = ConnectionDao.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from adminLogin where username = ? and password = ? and rights=? ");

			preparedStatement.setString(1, loginpojo.getUsername());
			preparedStatement.setString(2, loginpojo.getPassword());
			preparedStatement.setString(3, "admin");

			ResultSet rs = preparedStatement.executeQuery();
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				status = true;
			}
			rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				adminId = rs.getObject(1);
			}
			object[0] = status;
			object[1] = loginpojo.getUsername();
			object[2] = loginpojo.getPassword();
			object[3] = adminId;

			System.out.println("Admin>>>>" + Arrays.toString(object));

		} catch (SQLException e) {
			System.out.println(e);
		}
		return object;
	}

	public ResultSet getAllUsers() {
		ResultSet rs = null;
		System.out.println("In getAllUsers");
		try {
			rs = statement.executeQuery("SELECT * FROM login");
		} catch (SQLException m) {
			System.out.println("==>" + m);
		} finally {

		}
		return rs;
	}

	public Object[] validateSubAdmin(LoginPojo loginPojo) {
		boolean status = false;
		Object[] object = new Object[4];
		Object subAdminId = 0;

		try {
			Connection connection = ConnectionDao.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from subAdmin where username = ? and password = ? ");
			preparedStatement.setString(1, loginPojo.getUsername());
			preparedStatement.setString(2, loginPojo.getPassword());

			ResultSet rs = preparedStatement.executeQuery();
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				status = true;
			}
			object[0] = status;
			object[1] = loginPojo.getUsername();
			object[2] = loginPojo.getPassword();
			object[3] = subAdminId;

			System.out.println("Subadmin>>>" + Arrays.toString(object));
		} catch (SQLException e) {
			System.out.println(e);
		}
		return object;
	}

	public Object[] validateSuperAdmin(LoginPojo loginPojo) {
		boolean status = false;
		Object[] object = new Object[4];

		try {
			Connection connection = ConnectionDao.getConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from adminLogin where username = ? and password = ? and rights=? ");
			preparedStatement.setString(1, loginPojo.getUsername());
			preparedStatement.setString(2, loginPojo.getPassword());
			preparedStatement.setString(3, "superAdmin");

			ResultSet rs = preparedStatement.executeQuery();
			rs = preparedStatement.executeQuery();
			if (rs.next()) {
				status = true;
			}
			object[0] = status;
			object[1] = loginPojo.getUsername();
			object[2] = loginPojo.getPassword();

			System.out.println("SuperAdmin>>>" + Arrays.toString(object));
		} catch (SQLException e) {
			System.out.println(e);
		}
		return object;
	}

	public ResultSet getSuperAdmin() {
		ResultSet rs = null;
		System.out.println("In getAllUsers");
		try {
			rs = statement.executeQuery("SELECT * FROM adminLogin WHERE adminLogin.rights='superAdmin'");
		} catch (SQLException m) {
			System.out.println("==>" + m);
		} finally {

		}
		return rs;
	}

	public ResultSet getAllAdmins() {
		ResultSet rs = null;
		System.out.println("In getAllUsers");
		try {
			rs = statement.executeQuery("SELECT * FROM adminLogin WHERE adminLogin.rights='admin'");
		} catch (SQLException m) {
			System.out.println("==>" + m);
		} finally {

		}
		return rs;
	}
}