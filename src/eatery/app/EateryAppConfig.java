package eatery.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;


@ApplicationPath("/eatery")
public class EateryAppConfig extends ResourceConfig {

	public EateryAppConfig(){
		packages("eatery.rest");
	}
	
	
}
