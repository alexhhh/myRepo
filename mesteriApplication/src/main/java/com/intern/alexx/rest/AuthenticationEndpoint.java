package com.intern.alexx.rest;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intern.alexx.model.User; 
import com.intern.alexx.services.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Api(value = "/user", description = "Endpoint for users listing")
@Component 
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationEndpoint {

	@Autowired
	private UserService  userService;
	
	
	@POST
	@Path("/login")
	@ApiOperation(value = "Return OK", notes = "Return OK if user is logged",response = User.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User is logged in.",response = User.class ),
			@ApiResponse(code = 401, message = "Not authorized"),
			@ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response LogIn( User user  ) throws SQLException {
		 
		User asdasd =userService.getUser(user.getUserName(), user.getPassword());
		return Response.ok().entity(asdasd).build();
	}
	@POST
	@Path("/logout")
	@ApiOperation(value = "Return OK", notes = "Return OK if user is logged",response = User.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User is logged in.",response = User.class ),
			@ApiResponse(code = 401, message = "Not authorized"),
			@ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response LogOut( ) throws SQLException {
		 
		return Response.ok( ).build();
	}
	
	
	@POST
	@Path("/signup")
	@ApiOperation(value = "Return OK", notes = "Return OK if user is created",response = User.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User was successfuly created ",response = User.class ),
			@ApiResponse(code = 401, message = "Not authorized"),
			@ApiResponse(code = 404, message = "User was not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response AddUser(User user) throws Exception {
	  	 userService.insertUser(user);
		return Response.ok().build();
	}
	
	
	@GET 
	@Path("/query")
	@ApiOperation(value = "Return OK", notes = "Return OK if user is logged",response = User.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User is logged in.",response = User.class ),
			@ApiResponse(code = 401, message = "Not authorized"),
			@ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response checkUser(@QueryParam("userName") String userName) {
		User dbUser =userService.getUserByName(userName);
		return Response.ok().entity(dbUser).build();
	}
	
	@GET 
	@Path("/activate/query")
	@ApiOperation(value = "Return OK", notes = "Return OK if user is logged",response = User.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User is logged in.",response = User.class ),
			@ApiResponse(code = 401, message = "Not authorized"),
			@ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response checkUserToken(@QueryParam("tokenId") String tokenId) {
		 userService.activateUser(tokenId);
		return Response.ok().build();
	}
}
