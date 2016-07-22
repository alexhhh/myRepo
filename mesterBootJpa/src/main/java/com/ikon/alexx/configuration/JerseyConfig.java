package com.ikon.alexx.configuration;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;  
 
@Configuration 
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {
	
public JerseyConfig() {
		final String restEndpointsPackge = "com.ikon.alexx.rest";  //sau rest - care o sa fie mai imed		
		final String jacksonPackage = "org.codehaus.jackson.jaxrs";
		final String swaggerJaxrsJsonPackage = "com.wordnik.swagger.jaxrs.json";
		final String swaggerJaxrsListingPackage = "com.wordnik.swagger.jaxrs.listing";
		 
		packages(restEndpointsPackge, swaggerJaxrsJsonPackage, swaggerJaxrsListingPackage, jacksonPackage);		                
 	  //  register(MultiPartFeature.class);	
 	    register(CORSResponseFilter.class);
}

//private void configureSwagger() {
//    register(ApiListingResourceJSON.class);
//    register(ApiDeclarationProvider.class);
//    register(ResourceListingProvider.class);
//    BeanConfig beanConfig = new BeanConfig();
//    beanConfig.setVersion("1.0.2");   
//    beanConfig.setBasePath("http://localhost:8080/rest");
//    beanConfig.setResourcePackage("com.ikon.alexx.rest");    
//    beanConfig.setScan(true); 
//}
}
