package eatery.rest;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eatery.dao.OwnerDAO;
import eatery.exceptions.AppException;
import eatery.model.Owner;




@Path("/owner")
public class OwnerController {

	@GET
	@Path("/isvalid/{email}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse check(@PathParam("email")String email, @PathParam("password")String password ){
		AppResponse resp = new AppResponse();
		Owner o = new Owner();
		o.setEmail(email);
		o.setPassword(password);
		OwnerDAO dao = new OwnerDAO();
		try {
			dao.isTrue(o);
			resp.setMessage("success");
			resp.setPayLoad(o);
			resp.setStatus("confirm");
		} catch (AppException e) {
			resp.setMessage("fail");
			resp.setPayLoad(null);
			resp.setStatus("error");
		}

		return resp;
	}

	
	
	
	
	
	
	
	
	
	
}
