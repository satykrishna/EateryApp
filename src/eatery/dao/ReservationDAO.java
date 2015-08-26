package eatery.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;import java.util.Random;
import java.sql.PreparedStatement ;

import eatery.exceptions.AppException;
import eatery.model.Reservation;
import eatery.model.Restaurant;
import eatery.utils.DBUtil;

public class ReservationDAO {


	private final static int RESERVE_GAP = 2;



	public List<Integer> getNonReservedTables(String date) throws AppException{
		
		List<Reservation> reserved = assignedTables(date);
		RestaurantDAO dao = new RestaurantDAO();
		Restaurant r = dao.get();
		List<Integer>tablesreserved = new LinkedList<Integer>();
		List<Integer>tablesNotReserved = new LinkedList<Integer>();
		for(Reservation res : reserved){
			System.out.println("confirm code" + res.getConfirmcode());
			System.out.println("Table No is "+res.getTableNo());
			tablesreserved.add(res.getTableNo());
		}
		
		
		for(Integer i : tablesreserved){
			System.out.println("TABLES RESERVED ARE" + i);
		}
		int tables[] = new int[r.getNoofTables()];
		for(int i = 0; i < tables.length; i++){
			tables[i] = i;
		}
		
		for(Integer i : tablesreserved){
			tables[i] = 0;
			System.out.println("Table reserved is:" + i);
		}
		
		
		for(int i = 0; i < tables.length; i++){
			if(tables[i] != 0){
				tablesNotReserved.add(i);
			}
		}

		
		for(Integer i : tablesNotReserved){
			System.out.print("tables = " + i + " ");
		}
		
		System.out.println("done");
		
		return tablesNotReserved;
		
	}
	
	
	
	public Reservation assignTable(Reservation newReservation, List<Reservation> list, String type) throws AppException{
		int tableNo = -1;
		RestaurantDAO dao = new RestaurantDAO();
		Restaurant r = dao.get();
		List<Integer>tablesreserved = new LinkedList<Integer>();
		
		if(type == "modify"){
			list.remove(newReservation);
		}
		
		for(Reservation res : list){
			if(res.getConfirmcode() == "C"){
				tablesreserved.add(res.getTableNo());
			}
		}
		
		
		int tables[] = new int[r.getNoofTables()];

		for(int i = 0; i < tables.length; i++){
			tables[i] = i;
		}

		for(Integer i : tablesreserved){
			tables[i] = 0;
		}

		boolean allreserved = true;
		for(int i = 0; i < tables.length; i++){
			if(tables[i] != 0){
				allreserved = false;
			}
		}

		if(allreserved){
			newReservation.setTableNo(0);
			newReservation.setConfirmcode("W");

		}else{
			tableNo = (int)(Math.random()*(r.getNoofTables()));
			System.out.println("table No before while" + tableNo);
			while(tables[tableNo] == 0){
				System.out.println("tableNo in while" + tableNo + "tables[tableNo]  =" +tables[tableNo] );
				tableNo = (int)(Math.random()*(r.getNoofTables()));
				System.out.println("changed to " + tableNo);
			}
			newReservation.setTableNo(tableNo);
			newReservation.setReservestatus("C");
		}
		return newReservation;
	}




	public List<Reservation> assignedTables(String date) throws AppException{
		LinkedList<Reservation> reservationList = new LinkedList<Reservation>();
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from reservation where reserveDate like '%" +date+"%'";

		try {
			System.out.println("MY QUERY IS " +query);
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()){
				Reservation res = new Reservation();
				res.setCustomername(rs.getString("customername"));
				System.out.println(res.getCustomername());
				res.setEmail(rs.getString("email"));
				res.setPhoneNo(rs.getString("phoneNo"));
				res.setTableNo(rs.getInt("tableNo"));
				res.setPartySize(rs.getInt("partySize"));
				res.setReserveDate(rs.getString("reserveDate"));
				res.setReserveTime(rs.getString("reserveTime"));
				res.setSpecialneed(rs.getString("specialneed"));
				res.setReservestatus(rs.getString("reservestatus"));
				res.setConfirmcode(rs.getString("confirmcode"));
				System.out.println(res.getConfirmcode());
				reservationList.add(res);
			}	
		} catch (SQLException e) {
			System.out.println("ERROR : UNABLE TO FETCH RECORDS FROM DATABASE");
			e.printStackTrace();
			throw new AppException("ERROR: UNABLE TO FETCH ALL RESERVATION DETAILS!!!", e.getCause());
		}finally{
			DBUtil.close(ps, rs, con);
		}
		return reservationList;
	}


	public String getStatus(int tableNo, String date, String time) throws AppException{
		String status = "C";
		LinkedList<Reservation> reservationList = new LinkedList<Reservation>();
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from reservation where reserveDate like '" +date+"'";
		ArrayList<Integer> reservedTables = new ArrayList<Integer>();

		try {

			System.out.println("MY QUERY IS " +query);
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()){
				Reservation res = new Reservation();
				res.setCustomername(rs.getString("customername"));
				System.out.println(res.getCustomername());
				res.setEmail(rs.getString("email"));
				res.setPhoneNo(rs.getString("phoneNo"));
				res.setTableNo(rs.getInt("tableNo"));
				res.setPartySize(rs.getInt("partySize"));
				res.setReserveDate(rs.getString("reserveDate"));
				res.setReserveTime(rs.getString("reserveTime"));
				res.setSpecialneed(rs.getString("specialneed"));
				res.setReservestatus(rs.getString("reservestatus"));
				res.setConfirmcode(rs.getString("confirmcode"));
				System.out.println(res.getConfirmcode());
				reservationList.add(res);
				reservedTables.add(res.getTableNo());
			}


			int resHours = Integer.parseInt(time.substring(0,2));

			for(Reservation r: reservationList){
				String resTime	 = r.getReserveTime().substring(11, 19);
				int hours = Integer.parseInt(resTime.substring(0,2));
				if(Math.abs(hours-resHours)> RESERVE_GAP){
					status = "C";
					System.out.println("DIFF HOURS IS " + Math.abs(hours-resHours));
				}

				else
					status = "W";
			}

		} catch (SQLException e) {
			System.out.println("ERROR : UNABLE TO FETCH RECORDS FROM DATABASE");
			e.printStackTrace();
			throw new AppException("ERROR: UNABLE TO FETCH ALL RESERVATION DETAILS!!!", e.getCause());
		}finally{
			DBUtil.close(ps, rs, con);
		}



		return status;
	}

	
	/*GET CUSTOMER DETAILS*/
	public List<Reservation> getCustomerDetails(String customername) throws AppException{
		List<Reservation> reservationlist = new ArrayList<Reservation>();
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from reservation where customername like '%"+customername+"%'";
		try {

			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			System.out.println(query);
			while(rs.next()){
				Reservation res = new Reservation();
				res.setCustomername(rs.getString("customername"));
				System.out.println(res.getCustomername());
				res.setEmail(rs.getString("email"));
				res.setPhoneNo(rs.getString("phoneNo"));
				res.setTableNo(rs.getInt("tableNo"));
				res.setPartySize(rs.getInt("partySize"));
				res.setReserveDate(rs.getString("reserveDate"));
				res.setReserveTime(rs.getString("reserveTime"));
				res.setSpecialneed(rs.getString("specialneed"));
				res.setReservestatus(rs.getString("reservestatus"));
				res.setConfirmcode(rs.getString("confirmcode"));
				System.out.println(res.getConfirmcode());
				reservationlist.add(res);
			}
		} catch (SQLException e) {
			System.out.println("ERROR : UNABLE TO FETCH RECORDS FROM DATABASE");
			e.printStackTrace();
			throw new AppException("ERROR: UNABLE TO FETCH ALL RESERVATION DETAILS!!!", e.getCause());
		}finally{
			DBUtil.close(ps, rs, con);
		}
		return reservationlist;
	}




	/*CHECK IF ANY OTHER RESERVATIONS COLLIDE WITH SAME DATE AND TIME */


	public List<Reservation> getTable() throws AppException {
		List<Reservation> reservationlist = new ArrayList<Reservation>();
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "select * from reservation";
            System.out.println(query);
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()){
				Reservation res = new Reservation();
				res.setCustomername(rs.getString("customername"));
				res.setEmail(rs.getString("email"));
				res.setPhoneNo(rs.getString("phoneNo"));
				res.setTableNo(rs.getInt("tableNo"));
				res.setPartySize(rs.getInt("partySize"));
				res.setReserveDate(rs.getString("reserveDate"));
				res.setReserveTime(rs.getString("reserveTime"));
				res.setSpecialneed(rs.getString("specialneed"));
				res.setReservestatus(rs.getString("reservestatus"));
				res.setConfirmcode(rs.getString("confirmcode"));

			}
		} catch (SQLException e) {
			System.out.println("ERROR : UNABLE TO FETCH RECORDS FROM DATABASE");
			e.printStackTrace();
			throw new AppException("ERROR: UNABLE TO FETCH ALL RESERVATION DETAILS!!!", e.getCause());
		}finally{
			DBUtil.close(ps, rs, con);
		}
		return reservationlist;

	}



	public boolean checkstatus(Reservation res) {
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean canReserve = false;
		String query = "select count(*) from reservation where reserveDate =?";
		try{
			ps = con.prepareStatement(query);
			ps.setString(1, res.getReserveDate());
			rs = ps.executeQuery();
			if(rs.getInt(1) < 40){
				canReserve = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(ps, rs, con);
		}
		return canReserve;

	}


	/*DELETE A RESERVATION*/
	public boolean delete(Reservation res) throws AppException {

		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "DELETE from reservation WHERE confirmcode = ?";
		try{
			ps = con.prepareStatement(query);
			ps.setString(1, res.getConfirmcode());
			int affected =  ps.executeUpdate();
			if(affected == 1)
				return true;
			else{
				throw new AppException("ERROR : UNABLE TO DELETE THE RESERVATION WITH THE GIVEN CONFIRMATION CODE "+res.getConfirmcode());
			}
		}catch (SQLException e) {
			System.out.println("ERROR : UNABLE TO DELETE THE RESERVATION WITH THE GIVEN DETAILS "+ res.getConfirmcode());
			e.printStackTrace();
			throw new AppException("ERROR : UNABLE TO DELETE THE RESERVATION WITH THE GIVEN CONFIRMATION CODE "+res.getConfirmcode(), e.getCause());
		}finally{
			DBUtil.close(ps, rs, con);
		}
	}





	/*CHECK IF A PARTICULAR RESERVATION EXISTS with confirmation code*/

	public Reservation check(String confirmcode) throws AppException{
		Reservation res = new Reservation();
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;		
		try {
			String query = "select * from reservation where confirmcode	 = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, confirmcode);
			rs = ps.executeQuery();
			if(rs.next()){
				res.setCustomername(rs.getString("customername"));
				res.setEmail(rs.getString("email"));
				res.setPhoneNo(rs.getString("phoneNo"));
				res.setTableNo(rs.getInt("tableNo"));
				res.setPartySize(rs.getInt("partySize"));
				res.setReserveDate(rs.getString("reserveDate"));
				res.setReserveTime(rs.getString("reserveTime"));
				res.setSpecialneed(rs.getString("specialneed"));
				res.setReservestatus(rs.getString("reservestatus"));
				res.setConfirmcode(rs.getString("confirmcode"));
			}else{
				throw new AppException("Reservation With confirmation Number = "+confirmcode + " does not exist in our system" );
			}
		} catch (SQLException e) {
			System.out.println("ERROR :UNABLE TO FETCH RESERVATION DETAILS with confirmation number  "+confirmcode +"FROM DATABASE");
			e.printStackTrace();
			throw new AppException("ERROR: UNABLE TO RESERVATION DETAILS with confirmation number  "+confirmcode +"FROM DATABASE", e.getCause());
		}finally{
			DBUtil.close(ps, rs, con);
		}

		return res;
	}


	/*MODIFY EXISTING RESERVATION*/
	public Reservation modify(Reservation res) throws AppException {	
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "UPDATE reservation SET reserveDate = ?, reserveTime = ?, partySize = ?, reservestatus = ?"
				+ " WHERE confirmcode= ?";
		System.out.println("FIRST QUERY "  + query);
		
		List<Reservation> resList = assignedTables(res.getReserveDate());
	
	
			res = assignTable(res, resList, "modify");
	
	
		
		try{
			ps = con.prepareStatement(query);
			ps.setString(1, res.getReserveDate());
			ps.setString(2, res.getReserveTime());
			ps.setInt(3, res.getPartySize());
			ps.setString(4, res.getReservestatus());
			ps.setString(5, res.getConfirmcode());
			ps.executeUpdate();
			ps.close();
		}catch (SQLException e) {
			System.out.println("ERROR : UNABLE TO MODIFY THE RESERVATION WITH THE GIVEN DETAILS "+ res.getConfirmcode());
			e.printStackTrace();
			throw new AppException("ERROR : UNABLE TO MODIFY THE RESERVATION WITH THE GIVEN CONFIRMATION CODE "+res.getConfirmcode(), e.getCause());
		}finally{
			DBUtil.close(ps, rs, con);
		}
		return res;




	}	


	/*CREATE A NEW RESERVATION AND RETURN THE CONFIRMATION CODE */

	public Reservation create(Reservation res) throws AppException{

		res.setConfirmcode(generateConfirmationCode());		
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Reservation>list = assignedTables(res.getReserveDate());
		RestaurantDAO dao = new RestaurantDAO();
        Restaurant r = dao.get();
	
		res  = assignTable(res, list, "new");
	
	
	
	
		String query = "INSERT INTO eatery_db.reservation(customername, email, phoneNo, tableNo, partySize, reserveDate, reserveTime, specialneed, reservestatus, confirmcode) VALUES"
				+ "( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, res.getCustomername());
			ps.setString(2, res.getEmail());
			ps.setString(3, res.getPhoneNo());
			ps.setInt(4, res.getTableNo());
			ps.setInt(5, res.getPartySize());
			ps.setString(6, res.getReserveDate());
			ps.setString(7, res.getReserveTime());
			ps.setString(8, res.getSpecialneed());
			ps.setString(9, res.getReservestatus());
			ps.setString(10, res.getConfirmcode());
			System.out.println("RESERVATION DATE IS" + res.getReserveDate());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ERROR: UNABLE TO INSERT A NEW RESERVATION RECORD IN TO DATABASE");
			e.printStackTrace();
			throw new AppException("ERROR: UNABLE TO INSERT A NEW RESERVATION RECORD IN TO DATABASE", e.getCause());
		}finally{
			DBUtil.close(ps, rs, con);
		}
		return res;
	}

	/*GETS ALL RESERVATION LIST*/
	public List<Reservation> getAll() throws AppException{
		List<Reservation> reservationlist = new ArrayList<Reservation>();
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "select * from reservation";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			System.out.println(query);

			while(rs.next()){
				Reservation res = new Reservation();
				res.setCustomername(rs.getString("customername"));
				System.out.println(res.getCustomername());
				res.setEmail(rs.getString("email"));
				res.setPhoneNo(rs.getString("phoneNo"));
				res.setTableNo(rs.getInt("tableNo"));
				res.setPartySize(rs.getInt("partySize"));
				res.setReserveDate(rs.getString("reserveDate"));
				res.setReserveTime(rs.getString("reserveTime"));
				res.setSpecialneed(rs.getString("specialneed"));
				res.setReservestatus(rs.getString("reservestatus"));
				res.setConfirmcode(rs.getString("confirmcode"));
				reservationlist.add(res);
			}
		} catch (SQLException e) {

			System.out.println("ERROR : UNABLE TO FETCH RECORDS FROM DATABASE");
			e.printStackTrace();
			throw new AppException("ERROR: UNABLE TO FETCH ALL RESERVATION DETAILS!!!", e.getCause());
		}finally{
			DBUtil.close(ps, rs, con);
		}
		return reservationlist;
	}

	/*GENERATE A RANDOM CONFIRMATION CODE */
	private String generateConfirmationCode() {
		final String code = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int length = code.length();
		Random r = new Random();
		char []confirmation = new char[4];

		for(int i = 0; i < confirmation.length; i++){
			confirmation[i] = code.charAt(r.nextInt(length));
		}
		return new String(confirmation);
	}


	public static void main(String[] args) throws AppException {
		ReservationDAO dao  = new ReservationDAO();
		dao.getNonReservedTables("2016-12-15");



	}



	public Reservation update(Reservation res) throws AppException {
		Connection con = DBUtil.connectToDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "UPDATE reservation SET tableNo = ?"
				+ " WHERE confirmcode= ?";
		
		try{
			ps = con.prepareStatement(query);
			ps.setInt(1, res.getTableNo());
			ps.setString(2, res.getConfirmcode());
			ps.executeUpdate();
			ps.close();
		}catch (SQLException e) {
			System.out.println("ERROR : UNABLE TO MODIFY THE RESERVATION WITH THE GIVEN DETAILS "+ res.getConfirmcode());
			e.printStackTrace();
			throw new AppException("ERROR : UNABLE TO MODIFY THE RESERVATION WITH THE GIVEN CONFIRMATION CODE "+res.getConfirmcode(), e.getCause());
		}finally{
			DBUtil.close(ps, rs, con);
		}
		return res;

	}


}
