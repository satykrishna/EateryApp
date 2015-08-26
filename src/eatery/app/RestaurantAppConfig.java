package eatery.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;


@ApplicationPath("/restaurant")
public class RestaurantAppConfig extends ResourceConfig {

	
	public RestaurantAppConfig(){
		packages("eatery.rest");
	}
	
	
}
