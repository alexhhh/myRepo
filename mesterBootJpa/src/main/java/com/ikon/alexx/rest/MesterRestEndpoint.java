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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ikon.alexx.model.AreaSearchCriteria;
import com.ikon.alexx.model.FullMester;
import com.ikon.alexx.model.MesterDTO;
import com.ikon.alexx.model.MesterSearchCriteria;
import com.ikon.alexx.model.MyPage;
import com.ikon.alexx.service.MesterService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Api(value = "/mester", description = "Endpoint for mester listing")
@Component
@Path("/mester")
@Produces(MediaType.APPLICATION_JSON)
public class MesterRestEndpoint {

	@Autowired
	private MesterService mesterService;

	@Context
	private UriInfo uriInfo;

	@GET
	@Path("/query")
	@ApiOperation(value = "Return mester", notes = "Return one mester", response = FullMester.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Mester was retrieved successfully", response = FullMester.class),
			@ApiResponse(code = 404, message = "Mester not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getById(@QueryParam("idMester") String idMester) throws SQLException {
		FullMester mester = mesterService.getMesterById(idMester);
		int status = mester == null ? 404 : 200;
		return Response.ok(status).entity(mester).build();
	}
	
	@GET
	@Path("/user/query")
	@ApiOperation(value = "Return mester", notes = "Return one mester", response = FullMester.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Mester was retrieved successfully", response = FullMester.class),
			@ApiResponse(code = 404, message = "Mester not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getByUserId(@QueryParam("userId") String userId) throws SQLException {
		FullMester mester = mesterService.getMesterByUserId(userId);
		int status = mester == null ? 404 : 200;
		return Response.ok(status).entity(mester).build();
	}

	@POST
	@ApiOperation(value = "Add mester", notes = "Saves full a mester.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Meseter was successfuly created "),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response insert(MesterDTO mester) {
		mesterService.insertMester(mester);
		return Response.ok(Status.CREATED).entity(mester).build();
	}

	@PUT
	@ApiOperation(value = "Update mester", notes = "Update a full mester", response = FullMester.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Update mester was successful", response = FullMester.class),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response update(FullMester mester) {
		mesterService.updateMester(mester);
		return Response.ok(Status.OK).entity(mester).build();
	}

	@DELETE
	@ApiOperation(value = "Remove mester by id", notes = "Remove a mester by id", response = MesterDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Delete mester was successful", response = MesterDTO.class),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response delete(String idMester) {
		mesterService.deleteMester(idMester);
		return Response.ok().build();
	}

	@POST
	@Path("/search")
	@ApiOperation(value = "Return a page", notes = "Return a mester page")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Mester page was successfully retrieved."),
			@ApiResponse(code = 404, message = "The page was not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response search(MesterSearchCriteria searchCriteria) throws SQLException {
		MyPage<FullMester> newMester = mesterService.searchMester(searchCriteria);
		return Response.ok(Status.OK).entity(newMester).build();
	}

	@POST
	@Path("/area")
	@ApiOperation(value = "Return a page", notes = "Return a mester page", response = MesterDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Mester page was successfully retrieved.", response = MesterDTO.class),
			@ApiResponse(code = 404, message = "The page was not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response searchByArea(AreaSearchCriteria areaSearchCriteria) throws SQLException {
		List<FullMester> mesterList = mesterService.searchMesterByArea(areaSearchCriteria);
		return Response.ok(Status.OK).entity(mesterList).build();
	}

	// @POST
	// @Path("/Id={idMester}")
	// @ApiOperation(value = "Add speciality to mester", notes = "Add
	// speciality.", response = Speciality.class)
	// @ApiResponses(value = {
	// @ApiResponse(code = 200, message = "Speciality was successfully add.",
	// response = Speciality.class),
	// @ApiResponse(code = 404, message = "Speciality was not found."),
	// @ApiResponse(code = 500, message = "Internal server error.") })
	// public Response addOneSpeciality(@PathParam("idMester")String idMester,
	// String specialityName) {
	// mesterService.insertMesterSpeciality(specialityName, idMester);
	// return Response.ok().build();
	// }
	//
	// @DELETE
	// @Path("/Id={idMester}")
	// @ApiOperation(value = "Delete a mester speciality", notes = "Delete
	// speciality", response = Speciality.class)
	// @ApiResponses(value = {
	// @ApiResponse(code = 200, message = "Speciality was successfuly deleted.",
	// response = Speciality.class),
	// @ApiResponse(code = 500, message = "Internal server error.") })
	// public Response delete(String specialityName,@PathParam("idMester")
	// String idMester) {
	// mesterService.deleteOneMesterSpeciality(specialityName, idMester);
	// return Response.ok(200).build();
	// }

}
