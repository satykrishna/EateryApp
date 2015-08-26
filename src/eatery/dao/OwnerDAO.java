package eatery.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eatery.exceptions.AppException;
import eatery.model.Owner;
import eatery.utils.DBUtil;

public class OwnerDAO {



	public boolean isTrue(Owner owner) throws AppException{

		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean exists = false;
		String query = "select count(*) as count from owner where email = ? and password = ?";

		try{
			ps = con.prepareStatement(query);
			ps.setString(1,  owner.getEmail());
			ps.setString(2, owner.getPassword());
			rs = ps.executeQuery();
			int count = 0;
			if(rs.next()){
				count = rs.getInt("count");
			}
			if(count != 1)
				throw new AppException("ERROR: USERNAME/PASSWORD IS NOT VALID");
			exists = true;
		}catch(SQLException e){
			throw new AppException("ERROR : UNABLE TO FETCH RECORDS FROM DATABASE");
		}finally{
			DBUtil.close(ps, rs, con);
		}
		return exists;
	}






}
