package eatery.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;


@ApplicationPath("/head")
public class OwnerAppConfig extends ResourceConfig {

	public OwnerAppConfig(){
		packages("eatery.rest");
	}
	
	
}
