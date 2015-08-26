package eatery.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {


	private final static String URL = "jdbc:mysql://localhost:3306/eatery_db";
	private final static String username = "root";
	private final static String password = "Infy!123";


	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded successfully");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not loaded properly");
		}
	}

	public static Connection connectToDB(){

		Connection con = null;
		try {
			con = DriverManager.getConnection(URL, username, password);
			System.out.println("connection is established successfully");
		} catch (SQLException e) {
			System.out.println("ERROR: UNABLE TO ESTABLISH CONNECTION TO DB");
		}

		return con;
	}
	
	
	public static void close(PreparedStatement ps, ResultSet rs, Connection con){
		try {
			if(ps != null)
				ps.close();
			if(rs != null)
				rs.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println("ERROR: EITHER PREPARED STATEMENT OR RESULT SET OR CONNECTION TO DB is not closed properly");
		}

	}
	

}
