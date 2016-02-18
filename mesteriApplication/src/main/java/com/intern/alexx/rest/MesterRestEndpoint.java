package com.intern.alexx.rest;

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

	 @Context
	 private UriInfo uriInfo;
	
	@GET
	@Path("{id}")
	@ApiOperation(value = "Return mester", notes = "Return one mester", response = Mester.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Mester was retrieved successfully", response = Mester.class),
			@ApiResponse(code = 404, message = "Mester not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getById(@PathParam("id")Integer id) {
		Mester mester = new Mester();
			mester.setId(id);
			mesterService.getById(mester);
			int status = mester == null ? 404 :200;
		return Response.ok(status).entity(mester).build();
	}

	@GET
	@ApiOperation(value = "Return a page", notes = "Return a mester page", response = Mester.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Mester page was successfully retrieved.", response = Mester.class),
			@ApiResponse(code = 404, message = "The page was not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response search(MesterSearchCriteria searchCriteria) {
		MyPage<Mester> newMester = mesterService.searchMesterPage(searchCriteria);
		return Response.ok(Status.OK).entity(newMester).build();
	}
	
	@POST
	@ApiOperation(value = "Saves a new mester", notes = "Saves full a mester.")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Meseter was successfuly saved "),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response insert(Mester mester) {
		mesterService.insertMester(mester);
		return Response.ok(Status.OK).entity(mester).build();
	}

	@PUT
	@Path("{id}")
	@ApiOperation(value = "Update a mester", notes = "Update a full mester", response = Mester.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Update mester was successful", response = Mester.class),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response update(@PathParam("id")Integer id,Mester mester) {
		  mester = new Mester();
		mester.setId(id);
		mesterService.updateMester(mester);
		return Response.ok(Status.OK).entity(mester).build();
	}
	
	@DELETE
	@Path("{id}")
	@ApiOperation(value = "Removes mester given id", notes = "Remove a full mester", response = Mester.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Delete mester was successful", response = Mester.class),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response delete(@PathParam("id")Integer id) {
		Mester mester = new Mester();
		mester.setId(id);
		mesterService.deleteMester(mester);
		return Response.accepted().build();
	}
}
