package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class NotificationDao {
	
	public static Statement statement = ConnectionDao.getStatement();
	public static ResultSet rs;
	
	public static Object[] getNotificationDetails(String memberId) {
		System.out.println("In getNotificationDetails");
		Object[] object = new Object[2];
		try {
			rs = statement.executeQuery("SELECT * FROM notification WHERE memberId='" + memberId + "'");

			if (rs.next()) {
				object[0] = rs.getString(3);
				object[1] = rs.getString(4);
			}
			System.out.println("Object>>>" + Arrays.toString(object));
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return object;
	}
	
}
