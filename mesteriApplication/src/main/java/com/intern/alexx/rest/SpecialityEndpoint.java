package com.intern.alexx.rest;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intern.alexx.services.SpecialityService;
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
	@Path("/specialities")
	@ApiOperation(value = "Get specialities", notes = "Get specialities.", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "All specialities was successfully retrived.", response = String.class),
			@ApiResponse(code = 404, message = "Speciality page not found."),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getSpecialities() throws SQLException {
		List<String> specialities = specialityService.getAllSpeciality();
		return Response.ok(Status.OK).entity(specialities).build();
	}

	@GET
	@Path("/specialities/{idMester} ")
	@ApiOperation(value = "Get the  mester specialities", notes = "Get specialities.", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Mester specialities was successfully retrived.", response = String.class),
			@ApiResponse(code = 404, message = "Mester specialities not found."),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getMesterSpecialities(@PathParam("idMester") String idMester) throws SQLException {
		List<String> specialities = specialityService.getAllMesterSpeciality(idMester);
		return Response.ok(Status.OK).entity(specialities).build();
	}

	@POST
	@Path("/{idMester}")
	@ApiOperation(value = "Add speciality to mester", notes = "Add speciality.", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Speciality was successfully add.", response = String.class),
			@ApiResponse(code = 404, message = "Speciality was not found."),
			@ApiResponse(code = 500, message = "Internal server error.") })
	public Response addOneSpeciality(@PathParam("idMester") String idMester, String specialityName) {
		specialityService.insertMesterSpeciality(specialityName, idMester);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{specialityName}/{idMester}")
	@ApiOperation(value = "Delete a mester speciality", notes = "Delete speciality", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Speciality was successfuly deleted.", response = String.class),
			@ApiResponse(code = 500, message = "Internal server error.") })
	public Response delete(@PathParam("specialityName") String specialityName, @PathParam("idMester") String idMester) {
		specialityService.deleteOneMesterSpeciality(specialityName, idMester);
		return Response.ok().build();
	}

}