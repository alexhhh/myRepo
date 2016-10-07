package com.ikon.alexx.rest;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path; 
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
import com.ikon.alexx.model.SpecialityDTO;
import com.ikon.alexx.service.SpecialityService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
  

@Api(value = "/speciality", description = "Endpoint for speciality listing")
@Component
@Path("/speciality")
@Produces(MediaType.APPLICATION_JSON)
public class SpecialityEndpoint {

	@Autowired
	private SpecialityService specialityService;

	@GET
	@Path("/all")
	@ApiOperation(value = "Get specialities", notes = "Get specialities.", response = SpecialityDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "All specialities was successfully retrived.", response = SpecialityDTO.class),
			@ApiResponse(code = 404, message = "Speciality page not found."),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getSpecialities() throws SQLException {		 
		List<SpecialityDTO> specialities = specialityService.getAllSpeciality();
		return Response.ok(Status.OK).entity(specialities).build();
	}
	
	@POST
	@ApiOperation(value = "Add speciality ", notes = "Add speciality.", response = SpecialityDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Speciality was successfully add.", response = SpecialityDTO.class),
			@ApiResponse(code = 404, message = "Speciality was not found."),
			@ApiResponse(code = 500, message = "Internal server error.") })
	public Response addSpeciality(SpecialityDTO speciality) {
		specialityService.insertSpeciality(speciality);
		return Response.ok().entity(speciality).build();
	}
	
	@PUT
	@ApiOperation(value = "Update speciality ", notes = "Update speciality.", response = SpecialityDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Speciality was successfully updated.", response = SpecialityDTO.class),
			@ApiResponse(code = 404, message = "Speciality was not found."),
			@ApiResponse(code = 500, message = "Internal server error.") })
	public Response editSpeciality(SpecialityDTO speciality) {
		specialityService.updateSpeciality(speciality);
		return Response.ok().entity(speciality).build();
	}
	
	@DELETE
	@Path("/query")
	@ApiOperation(value = "Delete a speciality by id", notes = "Delete speciality by id" )
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Speciality was successfuly deleted."),
			@ApiResponse(code = 500, message = "Internal server error.") })
	public Response deleteSpeciality(@QueryParam("idSpeciality")String idSpeciality) {
		specialityService.deleteSpeciality(idSpeciality); 
		return Response.ok().build();
	}
 
	@GET
	@Path("/mester/query")
	@ApiOperation(value = "Get the  mester specialities", notes = "Get specialities.", response = SpecialityDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Mester specialities was successfully retrived.", response = SpecialityDTO.class),
			@ApiResponse(code = 404, message = "Mester specialities not found."),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getMesterSpecialities(@QueryParam("idMester") String idMester) throws SQLException {
		List<SpecialityDTO> specialities = specialityService.getAllMesterSpeciality(idMester);
		return Response.ok(Status.OK).entity(specialities).build();
	}


}
