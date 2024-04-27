package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class subAdminDao {
	public static Statement statement;
	public static ResultSet rs;
	static PreparedStatement pst;
	static PreparedStatement ps;
	public static Connection con;

	public subAdminDao() throws ClassNotFoundException {
		try {
			con = ConnectionDao.getConnection();
			System.out.println("Connecting with DataService");
			statement = con.createStatement();

		} catch (SQLException m) {
			System.out.println("==>-" + m);
		}
	}

	public ResultSet getAllSubAdmin() throws SQLException {
		ResultSet rs = null;
		System.out.println("In getAllSubAdmin");
		try {
			rs = statement.executeQuery("SELECT * FROM subAdmin");
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return rs;

	}

	public static int deleteSubAdmin(int id) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from subAdmin where id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int updateAdminUser(String username, String password, String agencies, String emailId,
			String userPermission, String totalActiveUsers, String rechargePermission, String commisionPercentage,
			String id) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"update subAdmin set username=?,password=?,nameOfAgencies=?,emailId=?,totalPermissionOfUser=?,totalNoOfHost=?,rechargePermission=?,commissionPercentage=? where id=?");
			ps.setObject(1, username);
			ps.setObject(2, password);
			ps.setObject(3, agencies);
			ps.setObject(4, emailId);
			ps.setObject(5, userPermission);
			ps.setObject(6, totalActiveUsers);
			ps.setObject(7, rechargePermission);
			ps.setObject(8, commisionPercentage);
			ps.setObject(9, id);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int insertSubAdmin() {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into login(username,password,emailId) values(?,?,?)");
//			ps.setObject(1, usernameAdd);
//			ps.setObject(2, passwordAdd);
//			ps.setObject(3, emailIdAdd);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public Object[] checkEmailAvialibility(String emailId, String agencies) {
		System.out.println("In checkEmailAvialibility");
		Object[] object = new Object[3];
		try {
			rs = statement.executeQuery(
					"SELECT * FROM subAdmin WHERE emailId='" + emailId + "' && nameOfAgencies='" + agencies + "'");

			if (rs.next()) {
				object[0] = rs.getString(4);// name of agencies
				object[1] = 1;
				object[2] = rs.getString(5);// EmailId
			}

		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return object;
	}

	public int insertSubadminDetails(String username, String password, String agencies, String emailId,
			String userPermission, String totalActiveUsers, String rechargePermission, String commisionPercentage) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"insert into subAdmin(username,password,nameOfAgencies,emailId,totalPermissionOfUser,rechargePermission,commissionPercentage) values(?,?,?,?,?,?,?)");
			ps.setObject(1, username);
			ps.setObject(2, password);
			ps.setObject(3, agencies);

			ps.setObject(4, emailId);
			ps.setObject(5, userPermission);
			ps.setObject(6, rechargePermission);

			ps.setObject(7, commisionPercentage);

			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public Object[] getSubAdminRechargeRight(String subadminUsername, String subadminPassword) {
		System.out.println("In getSubAdminRechargeRight");
		Object[] object = new Object[3];

		try {
			rs = statement.executeQuery("SELECT * FROM subAdmin where username='" + subadminUsername + "'&& password='"
					+ subadminPassword + "'");
			if (rs.next()) {
				object[0] = rs.getInt(1);
				object[1] = rs.getObject(8);// rechargePermission
			}

			if (object[1].toString().equals("yes")) {
				object[2] = true;
			} else {
				object[2] = false;
			}
			System.out.println(Arrays.toString(object));

		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return object;
	}

	public ResultSet getAllMembersAserSubAdmin(Object object) {
		System.out.println("In getAllMembersAserSubAdmin");
		try {
			rs = statement.executeQuery("SELECT * FROM profile where subAdminId='" + object + "'");
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return rs;
	}

	public ArrayList<String> getAgentInfo(int id) {
		ArrayList<String> al = new ArrayList<>();
		System.out.println("In getAgentInfo");
		try {
			rs = statement.executeQuery("SELECT * FROM subAdmin where id=" + id);
			while (rs.next()) {
				al.add(rs.getString(2));
				al.add(rs.getString(3));
				al.add(rs.getString(4));
				al.add(rs.getString(5));
				al.add(rs.getString(6));
				al.add(rs.getString(7));
				al.add(rs.getString(8));
				al.add(rs.getString(9));
				al.add(rs.getString(10));
			}

			System.out.println("ArrayList>>" + al);
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return al;
	}

}
