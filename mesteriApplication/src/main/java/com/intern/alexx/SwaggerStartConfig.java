package com.intern.alexx;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
 
@Configuration
@ApplicationPath("/rest")
public class SwaggerStartConfig extends ResourceConfig {
	
public SwaggerStartConfig() {
		
		final String restEndpointsPackge = "com.intern.alexx.rest";
		final String jacksonPackage = "org.codehaus.jackson.jaxrs";
		final String swaggerJaxrsJsonPackage = "com.wordnik.swagger.jaxrs.json";
		final String swaggerJaxrsListingPackage = "com.wordnik.swagger.jaxrs.listing";
		
		packages(restEndpointsPackge, swaggerJaxrsJsonPackage, swaggerJaxrsListingPackage, jacksonPackage);		                
	    register(MultiPartFeature.class);	
	    register(CORSResponseFilter.class);
}
}
