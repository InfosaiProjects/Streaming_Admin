package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FeedDao {

	public static Statement statement;
	public static ResultSet rs;
	static PreparedStatement pst;
	static PreparedStatement ps;
	public static Connection con;

	public FeedDao() throws ClassNotFoundException {
		try {
			con = ConnectionDao.getConnection();
			System.out.println("Connecting with DataService");
			statement = con.createStatement();

		} catch (SQLException m) {
			System.out.println("==>-" + m);
		}
	}

	public int insertFeedAsPerMember(String images, String memberId, String about) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into feed(memberId,about,image) values(?,?,?)");
			ps.setObject(1, memberId);
			ps.setObject(2, about);
	        ps.setObject(3, images);
	
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public ResultSet getAllFeed() {
		System.out.println("In getAllFeed");
		try {
			rs = statement.executeQuery("SELECT * FROM feed inner join profile on feed.memberId=profile.memberId ");
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return rs;
	}

	public ResultSet getFeedByMember(String memberId) {
		System.out.println("In getFeedByMember");
		try {
			rs = statement.executeQuery(
					"SELECT *,profile.`name` FROM feed inner join profile on feed.memberId=profile.memberId where feed.memberId='"
							+ memberId + "'ORDER BY  feed.id desc");
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return rs;

	}

	public ResultSet getLikesAndTotalGifts(String memberId, Object feedId) {
		System.out.println("In getLikesAndTotalGifts");
		try {
			rs = statement.executeQuery(
					"SELECT COUNT(feedLikesHistory.like),GROUP_CONCAT(giftId) FROM feedLikesHistory INNER JOIN feedGiftHistory ON feedGiftHistory.`memberId`=feedLikesHistory.`memberId`   WHERE feedLikesHistory.like <=1 && feedLikesHistory.memberId='"
							+ memberId + "'&& feedLikesHistory.`feedId`='" + feedId + "' && feedGiftHistory.`feedId`='"
							+ feedId + "'  GROUP BY feedLikesHistory.memberId");
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return rs;
	}
	
	public String[] getLikesAndTotalGiftsInStrings(String memberId, Object feedId) {
		String[] strAr1=new String[2];
		System.out.println("In getLikesAndTotalGiftsInStrings");
		try {
			rs = statement.executeQuery(
					"SELECT COUNT(feedLikesHistory.like),GROUP_CONCAT(giftId) FROM feedLikesHistory INNER JOIN feedGiftHistory ON feedGiftHistory.`memberId`=feedLikesHistory.`memberId`   WHERE feedLikesHistory.like <=1 && feedLikesHistory.memberId='"
							+ memberId + "'&& feedLikesHistory.`feedId`='" + feedId + "' && feedGiftHistory.`feedId`='"
							+ feedId + "'  GROUP BY feedLikesHistory.memberId");
		if(rs.next()) {
			strAr1[0]=rs.getString(1);
			strAr1[1]=rs.getString(2);
		}
		
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return strAr1;
	}

	public int putLikeOnMemberFeed(String memberId, String feedId, String likeOrUnlike, String userId) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"insert into feedLikesHistory(memberId,feedId,userId,feedLikesHistory.like) values(?,?,?,?)");
			ps.setObject(1, memberId);
			ps.setObject(2, feedId);
			ps.setObject(3, userId);
			ps.setObject(4, 2);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public Boolean checkTheFeedAvilableInTable(String memberId, String feedId, String likeOrUnlike, String userId) {
		System.out.println("In checkTheFeedAvilableInTable");
		Boolean status = false;

		try {
			rs = statement.executeQuery("select memberId,feedId,userId from feedLikesHistory where memberId='"
					+ memberId + "' && feedId='" + feedId + "' && userId='" + userId + "'");
			if (rs.next()) {
				if (!rs.getString(1).isEmpty()) {
					status = true;
				} else {
					status = false;
				}
			}
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return status;
	}

	public int updateLikeOnMemberFeed(String memberId, String feedId, String likeOrUnlike, String userId) {
		int status = 0;
		String likeId = "";
		try {

			Boolean like = checkLikeOrUnlikeByFeedAndMemberId(memberId, feedId, userId);

			if (like) {
				likeId = "0";
			} else {
				likeId = "1";
			}
			System.out.println("likeId>>" + likeId);
			PreparedStatement ps = con.prepareStatement(
					"update feedLikesHistory set feedLikesHistory.like=? where memberId=? && feedId=? && userId=?");
			ps.setObject(1, likeId);
			ps.setObject(2, memberId);
			ps.setObject(3, feedId);
			ps.setObject(4, userId);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;

	}

	private Boolean checkLikeOrUnlikeByFeedAndMemberId(String memberId, String feedId, String userId) {
		Boolean like = false;
		try {
			rs = statement.executeQuery("select feedLikesHistory.`like` from feedLikesHistory where memberId='"
					+ memberId + "' && feedId='" + feedId + "' && userId='" + userId + "'");
			if (rs.next()) {
				if (rs.getString(1).equals("1")) {
					like = true;
				} else {
					like = false;
				}
				System.out.println("like>>" + like);
			}
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return like;
	}

	public ResultSet getCommentsByMemberIdAndFeedId(String memberId, String feedId) {
		System.out.println("In getCommentsByMemberIdAndFeedId");
		try {
			rs = statement.executeQuery(
					"SELECT feedComments.*,`profile`.`name` FROM feedComments INNER JOIN streamingApp.profile ON streamingApp.`profile`.`memberId`=feedComments.`userId` WHERE feedComments.memberId= '"
							+ memberId + "' && feedId='" + feedId + "'");
		} catch (SQLException m) {
			System.out.println("==>" + m);
		}
		return rs;

	}

	public int insertCommentFeedAsPerMember(String memberId, String feedId, String userId, String comment) {
		int status = 0;
		try {
			Connection con = ConnectionDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("insert into feedComments(memberId,feedId,userId,comment) values(?,?,?,?)");
			ps.setObject(1, memberId);
			ps.setObject(2, feedId);
			ps.setObject(3, userId);
			ps.setObject(4, comment);
			status = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}
	
	public int getFeedCount() throws SQLException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int count = 0;
	    
	    try {
	    	con = ConnectionDao.getConnection();
	        String query = "SELECT COUNT(*) FROM feed";
	        ps = con.prepareStatement(query);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	//con.close();
	    }
	    
	    return count;
	}
	
	public ResultSet getPaginatedFeed(int startIndex, int pageSize) throws SQLException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
	    	con = ConnectionDao.getConnection();
	    	//SELECT * FROM feed inner join profile on feed.memberId=profile.memberId ORDER BY feed.insertedTime DESC LIMIT ?, ?
	        String query = "SELECT * FROM feed inner join profile on feed.memberId=profile.memberId ORDER BY feed.insertedTime DESC LIMIT ?, ?";
	        ps = con.prepareStatement(query);
	        ps.setInt(1, startIndex);
	        ps.setInt(2, pageSize);
	        rs = ps.executeQuery();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	     //   con.close();
	    }
	    
	    return rs;
	}
	
	//feedComment
	public ResultSet getFeedsAllMappedData(int feedId) throws SQLException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    
	    try {
	    	con = ConnectionDao.getConnection();
	    	//SELECT * FROM feed inner join profile on feed.memberId=profile.memberId ORDER BY feed.insertedTime DESC LIMIT ?, ?
	        String query = "SELECT distinct feed.id as feedId,feed.image as image,feed.insertedTime as dateinserted,\r\n"
	        		+ "feed.about as about,profile.name as memberName,profile.memberId as memberId,feedComments.comment as comments,\r\n"
	        		+ "profile.diamonds as totalDiamonds,\r\n"
	        		+ "(select count(*) from feedComments where  feedComments.feedId = feed.id) as totalComments,\r\n"
	        		+ "(select count(*) from feedLikesHistory where  feedLikesHistory.feedId = feed.id) as totalLike,\r\n"
	        		+ "feedLikesHistory.like as likeValue\r\n"
	        		+ "FROM feed inner join profile on feed.memberId=profile.memberId\r\n"
	        		+ "inner join feedComments on feedComments.feedId = feed.id\r\n"
	        		+ "inner join feedLikesHistory on feedLikesHistory.id =  feed.id\r\n"
	        		+ "where feed.id=?";
	        ps = con.prepareStatement(query);
	        ps.setInt(1, feedId);
	        rs = ps.executeQuery();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	     //   con.close();
	    }
	    
	    return rs;
	}


}
