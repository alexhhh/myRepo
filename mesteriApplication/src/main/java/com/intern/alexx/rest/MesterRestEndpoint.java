package com.intern.alexx.rest;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intern.alexx.model.Mester;
import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.services.MesterService;
import com.intern.alexx.services.SpecialityService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponses;
import com.wordnik.swagger.annotations.ApiResponse;

@Api(value = "/mesteri", description = "Endpoint for mester listing")
@Component
@Path("/mesteri")
@Produces(MediaType.APPLICATION_JSON)
public class MesterRestEndpoint {

	@Autowired
	private MesterService mesterService;
	
	@Autowired
	private SpecialityService specService;

	 @Context
	 private UriInfo uriInfo;
	
	@GET
	@Path("/{idMester}")
	@ApiOperation(value = "Return mester", notes = "Return one mester", response = Mester.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Mester was retrieved successfully", response = Mester.class),
			@ApiResponse(code = 404, message = "Mester not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getById(@PathParam("idMester")String idMester) {
		 Mester mester = mesterService.getById(idMester);
			int status = mester == null ? 404 : 200;
		return Response.ok(status).entity(mester).build();
	}

	@POST
	@Path("/search")
	@ApiOperation(value = "Return a page", notes = "Return a mester page", response = MyPage.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Mester page was successfully retrieved.", response = MyPage.class),
			@ApiResponse(code = 404, message = "The page was not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response search(MesterSearchCriteria searchCriteria) throws SQLException {
		MyPage<Mester> newMester = mesterService.searchMester(searchCriteria);
		return Response.ok(Status.OK).entity(newMester).build();
	}
	
	@POST
	@ApiOperation(value = "Add mester", notes = "Saves full a mester.")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Meseter was successfuly created "),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response insert(Mester mester) {
		mesterService.insertMester(mester);
		return Response.ok(Status.CREATED).entity(mester).build();
	}

	@PUT
	@Path("/{idMester}")
	@ApiOperation(value = "Update mester", notes = "Update a full mester", response = Mester.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Update mester was successful", response = Mester.class),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response update(@PathParam("idMester")String idMester,Mester mester) {
		mester.setId(idMester);
		mesterService.updateMester(mester);
		return Response.ok(Status.OK).entity(mester).build();
	}
	
	@DELETE
	@Path("/{idMester}")
	@ApiOperation(value = "Remove mester", notes = "Remove a full mester", response = Mester.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Delete mester was successful", response = Mester.class),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response delete(@PathParam("idMester")String idMester) {		 
		mesterService.deleteMester(idMester);
		return Response.ok().build();
	}
	
	@GET
	@Path("/specialities/{idMester} ")
	@ApiOperation(value = "Get the  mester specialities", notes = "Get specialities.", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Mester specialities was successfully retrived.", response = String.class),
			@ApiResponse(code = 404, message = "Mester specialities not found."),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getMesterSpecialities(@PathParam("idMester") String idMester) throws SQLException {
		List<String> specialities = specService.getAllMesterSpeciality(idMester);
		return Response.ok(Status.OK).entity(specialities).build();
	}

}
