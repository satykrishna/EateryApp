package eatery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eatery.exceptions.AppException;

import eatery.model.Restaurant;
import eatery.utils.DBUtil;

public class RestaurantDAO {


	/*GETS ALL RESERVATION LIST*/
	public Restaurant get() throws AppException{
		Restaurant r = new Restaurant();
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "select * from restaurant";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			System.out.println(query);

			if(rs.next()){
				r.setName(rs.getString(1));
				r.setContact(rs.getString(2));
				r.setEmail(rs.getString(3));
				r.setOpendate(rs.getString(4));
				r.setClosedate(rs.getString(5));
				System.out.println(r.getEmail());
				r.setOpentime(rs.getString(6));	
				r.setClosetime(rs.getString(7));
				r.setAddress(rs.getString(8));
				r.setAutoassign(rs.getString(9));
				r.setNoofTables(rs.getInt(10));
			
				System.out.println("OPEN DATE" + r.getOpendate());
			}else{
				throw new AppException("ERROR : UNABLE TO FETCH RESTAURANT DETAILS");
			}
		} catch (SQLException e) {

			System.out.println("ERROR : UNABLE TO FETCH RECORDS FROM DATABASE");
			e.printStackTrace();
			throw new AppException("ERROR: UNABLE TO FETCH ALL RESERVATION DETAILS!!!", e.getCause());
		}finally{
			DBUtil.close(ps, rs, con);
		}
		return r;
	}
	
	
	
	public Restaurant update(Restaurant res) throws AppException {
		
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "update restaurant set name = ?, contact = ?, email = ?, "
					+ " address = ?";
			System.out.println(query);
			ps = con.prepareStatement(query);
			ps.setString(1, res.getName());
			ps.setString(2, res.getContact());
			ps.setString(3, res.getEmail());
			ps.setString(4, res.getAddress());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {

			System.out.println("ERROR : UNABLE TO FETCH RECORDS FROM DATABASE");
			e.printStackTrace();
			throw new AppException("ERROR: UNABLE TO FETCH ALL RESERVATION DETAILS!!!", e.getCause());
		}finally{
			DBUtil.close(ps, rs, con);
		}
		return get();
	}
	
	
	public Restaurant updateAutoAssign(Restaurant res) throws AppException {
		
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
		    System.out.println("AUTO ASSIGN :" + res.getAutoassign() );
			String query = "update restaurant set autoassign = ?, opendate = ?, closedate = ?,  opentime = ?,"
					+ " closetime = ?";
			System.out.println(query);
			ps = con.prepareStatement(query);
			ps.setString(1, res.getAutoassign());
			ps.setString(2, res.getOpendate());
			ps.setString(3, res.getClosedate());
			ps.setString(4, res.getOpentime());
			ps.setString(5, res.getClosetime());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {

			System.out.println("ERROR : UNABLE TO FETCH RECORDS FROM DATABASE");
			e.printStackTrace();
			throw new AppException("ERROR: UNABLE TO FETCH ALL RESERVATION DETAILS!!!", e.getCause());
		}finally{
			DBUtil.close(ps, rs, con);
		}
		return get();
	}



}
