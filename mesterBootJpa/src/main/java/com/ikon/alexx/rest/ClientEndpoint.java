package com.ikon.alexx.rest;

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

import com.ikon.alexx.model.ClientDTO; 
import com.ikon.alexx.service.ClientService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Api(value = "/client", description = "Endpoint for client users listing")
@Component
@Path("/client")
@Produces(MediaType.APPLICATION_JSON)
public class ClientEndpoint {

	@Autowired
	private ClientService clientService;
	
	
	@GET
	@Path("/query")
	@ApiOperation(value = "Return OK", notes = "Return OK if client is in the db", response = ClientDTO.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Client found.", response = ClientDTO.class),
			@ApiResponse(code = 401, message = "Not authorized"),
			@ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response checkClient(@QueryParam("id") String id) {
		ClientDTO dbClient = clientService.getClientById(id);
		//int status = dbClient.getFirstName() == null ? 404 : 200;
		return Response.ok().entity(dbClient).build();
	}
	
	@POST
	@ApiOperation(value = "Add client", notes = "Saves a client.")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Meseter was successfuly created "),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response insert(ClientDTO client) {
		clientService.insertClient(client);
		return Response.ok(Status.CREATED).entity(client).build();
	}
	
	@PUT
	@ApiOperation(value = "Edit client", notes = "Edit a client.")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Meseter was successfuly edited "),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response update(ClientDTO client) {
		clientService.updateClient(client);
		return Response.ok().entity(client).build();
	}
	
	@DELETE
	@ApiOperation(value = "Delete a client by id", notes = "Delete client by id", response = ClientDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Client was successfuly deleted.", response = ClientDTO.class),
			@ApiResponse(code = 500, message = "Internal server error.") })
	public Response deleteSpeciality(String clientId) {
		clientService.deleteClient(clientId); 
		return Response.ok().build();
	}
	
}
