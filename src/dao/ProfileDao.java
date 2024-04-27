package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class ProfileDao {

	public static Statement statement;
	public static ResultSet rs;
	static PreparedStatement pst;
	static PreparedStatement ps;
	public static Connection con;

	public ProfileDao() throws ClassNotFoundException {
		try {
			con = ConnectionDao.getConnection();
			System.out.println("Connecting with DataService");
			statement = con.createStatement();

		} catch (SQLException m) {
			System.out.println("==>-" + m);
		}
	}

	public static Object[] getProfileDetails(String memberId) {
		Object[] object = new Object[8];
		System.out.println("In getProfileDetails");
		System.out.println("memberId>>"+memberId);
		
		try {
			rs = statement.executeQuery("SELECT * FROM streamingApp.profile WHERE memberId='" + memberId + "'");

			if (rs.next()) {

				object[0] = rs.getString(2);
				object[1] = rs.getString(3);
				object[3] = rs.getString(4);
				object[4] = rs.getString(5);
				object[5] = rs.getString(6);
				object[6] = rs.getString(7);
				object[7] = rs.getString(8);

			}
			System.out.println("Object>>>" + Arrays.toString(object));
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return object;}

	public static ResultSet getAllProfileDetails() {
		System.out.println("In getAllProfileDetails");
		try {
			rs = statement.executeQuery("SELECT * FROM streamingApp.profile");

		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return rs;
	}

	public static int deleteProfile(int id) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from profile where id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int updateProfile(String proPic, String name, String country, String desc, String id) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("update profile set name=?,country=?,description=?,id=? where id=?");
			ps.setObject(1, name);
			ps.setObject(2, country);
			ps.setObject(3, desc);
			ps.setObject(4, id);
			ps.setObject(5, id);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int insertCoinsInUsersAccount(String userId, String userCode, String range) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("update profile set coins=? where id=? && memberId=?");
			ps.setObject(1, range);
			ps.setObject(2, userId);
			ps.setObject(3, userCode);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static ResultSet getAllProfileDetailsAsPerAgency(String adminId) {
		System.out.println("In getAllProfileDetailsAsPerAgency");
		try {
			rs = statement.executeQuery("SELECT * FROM streamingApp.profile where subAdminId='" + adminId + "' ");

		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return rs;
	}

	public static int blockUserWithTime(int id, String blockTime) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = null;
			if (blockTime.equals("99")) {
				ps = con.prepareStatement("update profile set profile.status=?,blockTime=? where id=?");
				ps.setObject(1, "perBlock");
				ps.setObject(2, 9999);
				ps.setObject(3, id);

			} else {
				ps = con.prepareStatement("update profile set profile.status=?,blockTime=? where id=?");
				ps.setObject(1, "tempBlock");
				ps.setObject(2, blockTime);
				ps.setObject(3, id);
			}

			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int unBlockUser(int id) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("update profile set profile.status=?,blockTime=? where id=?");
			ps.setObject(1, "open");
			ps.setObject(2, 0);
			ps.setObject(3, id);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}
	
	public ArrayList<String> getMemberInfo(String id) {
		ArrayList<String> al = new ArrayList<>();
		System.out.println("In getMemberInfo");
		try {
			rs = statement.executeQuery("SELECT * FROM profile where memberId='"+id+"'");
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
				al.add(rs.getString(11));
				al.add(rs.getString(12));
				al.add(rs.getString(13));
				al.add(rs.getString(14));
				al.add(rs.getString(15));
			}
			System.out.println("ArrayList>>" + al);
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return al;
	}
	
}
