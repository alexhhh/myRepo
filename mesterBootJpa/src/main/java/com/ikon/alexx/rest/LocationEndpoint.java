package com.ikon.alexx.rest;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ikon.alexx.model.LocationDTO; 
import com.ikon.alexx.service.LocationService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Api(value = "/location", description = "Endpoint for speciality listing")
@Component
@Path("/location")
@Produces(MediaType.APPLICATION_JSON)
public class LocationEndpoint {
	
	
	@Autowired
	private LocationService locationService;

	private static Logger LOGGER = LoggerFactory.getLogger(LocationEndpoint.class);
	
	@GET
	@Path("/all")
	@ApiOperation(value = "Get locations", notes = "Get locations.", response = LocationDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "All locations was successfully retrived.", response = LocationDTO.class),
			@ApiResponse(code = 404, message = "Locations page not found."),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getLocations() throws SQLException {		 
		List<LocationDTO> locations = locationService.getAllLocations();
		return Response.ok(Status.OK).entity(locations).build();
	}
	
	@POST
	@Path("/new")
	@ApiOperation(value = "Add locations", notes = "Add locations.", response = LocationDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "The locations was successfully added.", response = LocationDTO.class),
			@ApiResponse(code = 404, message = "Locations  not found."),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response insertLocation(LocationDTO location ) throws SQLException {		 
	  locationService.insert(location);
		return Response.ok(Status.OK).entity(location).build();
	}
	
	@POST
	@Path("/all_by_ids")
	@ApiOperation(value = "Get locations", notes = "Get locations.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "The locations was successfully retrived."),
			@ApiResponse(code = 404, message = "Locations page not found."),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getLocationsByIds(List <String> ids ) throws SQLException {		
		LOGGER.info("da printeaza dracu' "+ids.toString());
		List<LocationDTO> locations = locationService.getLocationsByIds(ids);
		return Response.ok(Status.OK).entity(locations).build();
	}
	
	@PUT
	@Path("/mester")
	@ApiOperation(value = "Update location ", notes = "Update location.", response = LocationDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Location was successfully updated.", response = LocationDTO.class),
			@ApiResponse(code = 404, message = "Location was not found."),
			@ApiResponse(code = 500, message = "Internal server error.") })
	public Response editLocation(LocationDTO location) {
		locationService.update(location);
		return Response.ok().entity(location).build();
	}
	
	@GET
	@Path("/mester/query")
	@ApiOperation(value = "Get the  mester location", notes = "Get location.", response = LocationDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Mester location was successfully retrived.", response = LocationDTO.class),
			@ApiResponse(code = 404, message = "Mester location not found."),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getMesterSpecialities(@QueryParam("mesterId") String mesterId) throws SQLException {
		LocationDTO location = locationService.findByMesterId(mesterId);
		return Response.ok(Status.OK).entity(location).build();
	}
	
}
