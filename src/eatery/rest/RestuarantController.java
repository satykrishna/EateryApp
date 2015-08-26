package eatery.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;




import eatery.dao.RestaurantDAO;
import eatery.exceptions.AppException;
import eatery.model.Restaurant;


@Path("/fetch")
public class RestuarantController {
	
	@GET
	@Path("/details")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse fetch(){
		AppResponse resp = new AppResponse();		
		RestaurantDAO dao = new RestaurantDAO();
		try {
			Restaurant r = dao.get();
			resp.setMessage("success");
			resp.setPayLoad(r);
			resp.setStatus("confirm");
		} catch (AppException e) {
			resp.setMessage("fail");
			resp.setPayLoad(null);
			resp.setStatus("error");
		}
		return resp;
	}


	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AppResponse fetch(Restaurant res){
		AppResponse resp = new AppResponse();		
		RestaurantDAO dao = new RestaurantDAO();
		try {
			Restaurant r = dao.update(res);
			resp.setMessage("success");
			resp.setPayLoad(r);
			resp.setStatus("confirm");
		} catch (AppException e) {
			resp.setMessage("fail");
			resp.setPayLoad(null);
			resp.setStatus("error");
		}
		return resp;
	}

	@POST
	@Path("/updateAuto")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AppResponse upateAuto(Restaurant res){
		AppResponse resp = new AppResponse();		
		RestaurantDAO dao = new RestaurantDAO();
		try {
			Restaurant r = dao.updateAutoAssign(res);
			resp.setMessage("success");
			resp.setPayLoad(r);
			resp.setStatus("confirm");
		} catch (AppException e) {
			resp.setMessage("fail");
			resp.setPayLoad(null);
			resp.setStatus("error");
		}
		return resp;
	}

	

}
