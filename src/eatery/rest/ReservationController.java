package eatery.rest;

import java.util.List;





import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



import eatery.dao.ReservationDAO;
import eatery.exceptions.AppException;
import eatery.model.Reservation;


@Path("/reserve")
public class ReservationController {



	/*UPDATE TABLE  */		
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update/table")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse update(Reservation res){
		AppResponse resp = new AppResponse();
		ReservationDAO dao = new ReservationDAO();
		try {
			res = dao.update(res);
			resp.setPayLoad(res);
			resp.setMessage("modified");
			resp.setStatus("success");
		} catch (AppException e) {
			resp.setMessage(e.getMessage());
			resp.setStatus(AppResponse.ERROR);	
			resp.setPayLoad(null);
		}
		return resp;
	}
	
	
	
	/*GET CUSTOMER DETAILS*/
	@GET
	@Path("/customer/{customername}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getCustomer(@PathParam("customername")String customer){
		AppResponse resp = new AppResponse();
		ReservationDAO dao = new ReservationDAO();
		try {
			List<Reservation> resList = dao.getCustomerDetails(customer);
			resp.setPayLoad(resList);
			resp.setMessage("found");
			resp.setStatus("success");
		} catch (AppException e) {
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}




	/*GET UN RESERVED TABLE DETAILS*/	
	@GET
	@Path("/tables/{date}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getUnreserved(@PathParam("date") String date){
		List<Integer> tableList = null;
		AppResponse resp = new AppResponse();
		ReservationDAO dao = new ReservationDAO();
		try {
			tableList = dao.getNonReservedTables(date);
			resp.setPayLoad(tableList);
			resp.setMessage("fetched");
			resp.setStatus("success");
		} catch (AppException e) {
			resp.setMessage(e.getMessage());
			resp.setStatus(AppResponse.ERROR);
			resp.setPayLoad(null);
		}
		return resp;
	}


	/*GET SEATING AREA DETAILS*/	
	@GET
	@Path("/tables/all")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getTables(){

		List<Reservation> tables = null;
		AppResponse resp = new AppResponse();
		ReservationDAO dao = new ReservationDAO();
		try {
			tables = dao.getAll();
			resp.setPayLoad(tables);
			resp.setMessage("fetched");
			resp.setStatus("success");
		} catch (AppException e) {
			resp.setMessage(e.getMessage());
			resp.setStatus(AppResponse.ERROR);
			resp.setPayLoad(null);
		}
		return resp;
	}






	/*DELETE A RESERVATION */
	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AppResponse delete(Reservation res){
		AppResponse resp = new AppResponse();
		ReservationDAO dao = new ReservationDAO();
		try {
			boolean result = dao.delete(res);
			resp.setPayLoad(result);
			resp.setMessage("deleted");
			resp.setStatus("success");
		} catch (AppException e) {
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;		 
	}




	/*CHECK RESERVATION IF EXISTS*/
	@GET
	@Path("/check/{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse check(@PathParam("code")String code){
		System.out.println("CODE IS "+code);
		System.out.println();
		System.out.println();
		AppResponse resp = new AppResponse();
		ReservationDAO dao = new ReservationDAO();
		try {
			Reservation res = dao.check(code);
			resp.setPayLoad(res);
			resp.setMessage("found");
			resp.setStatus("success");
		} catch (AppException e) {
			e.printStackTrace();
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;

	}


	/*MODIFY RESERVATION */		
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/modify")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse modify(Reservation res){
		AppResponse resp = new AppResponse();
		ReservationDAO dao = new ReservationDAO();
		try {
			res = dao.modify(res);
			resp.setPayLoad(res);
			resp.setMessage("modified");
			resp.setStatus("success");
		} catch (AppException e) {
			resp.setMessage(e.getMessage());
			resp.setStatus(AppResponse.ERROR);	
			resp.setPayLoad(null);
		}
		return resp;
	}


	/*CREATE RESERVATION */	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse createNew(Reservation res){
		AppResponse resp = new AppResponse();
		System.out.println("DATE IS CREATE" + res.getReserveDate());
		ReservationDAO dao = new ReservationDAO();
		try {	
			dao.create(res);
			resp.setPayLoad(res);
			resp.setMessage("created");
			resp.setStatus("success");	
		} catch (AppException e) {
			resp.setMessage(e.getMessage());
			resp.setStatus(AppResponse.ERROR);	
			resp.setPayLoad(null);
		}
		return resp;
	}


	/* GET ALL RESERVATIONS FROM DB */
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getAll(){
		List<Reservation> resList = null;
		AppResponse resp = new AppResponse();
		ReservationDAO dao = new ReservationDAO();
		try {
			resList = dao.getAll();
			resp.setPayLoad(resList);
			resp.setMessage("fetched");
			resp.setStatus("success");
		} catch (AppException e) {
			resp.setMessage(e.getMessage());
			resp.setStatus(AppResponse.ERROR);
			resp.setPayLoad(null);
		}
		return resp;
	}






}
