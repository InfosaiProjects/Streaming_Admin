package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class CoinsDao {
	public static Statement statement;
	public static ResultSet rs;
	static PreparedStatement pst;
	static PreparedStatement ps;
	public static Connection con;

	public CoinsDao() throws ClassNotFoundException {
		try {
			con = ConnectionDao.getConnection();
			System.out.println("Connecting with DataService");
			statement = con.createStatement();

		} catch (SQLException m) {
			System.out.println("==>-" + m);
		}
	}

	public static Object[] getCoinsDetails(String memberId) {

		System.out.println("In getProfileDetails");
		Object[] object = new Object[2];
		try {
			rs = statement.executeQuery("SELECT SUM(coins) FROM coinsProfileDetails WHERE memberId='" + memberId + "'");

			if (rs.next()) {
				object[0] = rs.getString(1);

			}
			System.out.println("Object>>>" + Arrays.toString(object));
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return object;
	}

	public ResultSet getAllCoins() {
		System.out.println("In getAllCoins");
		try {
			rs = statement.executeQuery("SELECT * FROM coinsMaster");
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return rs;
	}

	public static int deleteCoins(int id) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from coinsMaster where id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int updateCoinsMaster(String coins, String rs2, String id) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("update coinsMaster set coins=?,rs=? where id=?");
			ps.setObject(1, coins);
			ps.setObject(2, rs2);
			ps.setObject(3, id);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int insertCoinsPlans(String coinsAdd, String rsAdd) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into coinsMaster(coins,rs) values(?,?)");
			ps.setObject(1, coinsAdd);
			ps.setObject(2, rsAdd);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public int insertCoinsAsPerMemberId(String paymentId, String coins, String rs2, String memberId) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"insert into coinsProfileDetails(coins,paymentId,paymentRs,memberId) values(?,?,?,?)");
			ps.setObject(1, coins);
			ps.setObject(2, paymentId);
			ps.setObject(3, rs2);
			ps.setObject(4, memberId);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int insertCoinsInAgencyAccount(String agencyCode, String range) {
		System.out.println("agencyCode>>" + agencyCode);
		System.out.println("range>>" + range);
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("update subAdmin set totalCoins=totalCoins+? where id=?");
			ps.setObject(1, range);
			ps.setObject(2, agencyCode);
			status = ps.executeUpdate();
			System.out.println("status>>" + status);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static Integer getTheCoinsByMemberId(String memberId) {
		System.out.println("In getTheCoinsByMemberId");
		int coin=0;
		try {
			rs = statement.executeQuery("SELECT * FROM coinsProfileDetails where memberId='" + memberId + "'");
			if(rs.next()) {
				coin=rs.getInt(5);
			}
			System.out.println("coin>>>"+coin);
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return coin;
	}

}
