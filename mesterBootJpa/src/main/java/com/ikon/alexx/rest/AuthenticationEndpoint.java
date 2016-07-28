package com.ikon.alexx.rest;

import java.nio.charset.Charset;
//import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.security.crypto.codec.Base64;

import com.ikon.alexx.model.TokenDTO;
import com.ikon.alexx.model.UserDTO;
import com.ikon.alexx.service.TokenService;
import com.ikon.alexx.service.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Api(value = "/user", description = "Endpoint for users listing")
@Component
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationEndpoint {

	private static Logger LOGGER = LoggerFactory.getLogger(AuthenticationEndpoint.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenService tokenService;

	@POST
	@Path("/login")
	@ApiOperation(value = "Return OK", notes = "Return OK if user is logged in", response = Map.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User is logged in.", response = Map.class),
			@ApiResponse(code = 401, message = "Not authorized"), @ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response LogIn(UserDTO user) throws SQLException {
		String userToken = encodeCredentialsInBase64(user.getUserName(), user.getPassword());
		user = userService.getUser(user.getUserName(), user.getPassword());
		String userRole = userService.getUserRole(user.getRoleId());
		Map<String, Object> map = new HashMap<>();
		map.put("token", userToken);
		map.put("user", user);
		map.put("role", userRole);
		return Response.ok().entity(map).build();
	}

	@POST
	@Path("/logout")
	@ApiOperation(value = "Return OK", notes = "Return OK if user is logged out", response = UserDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User is logged out.", response = UserDTO.class),
			@ApiResponse(code = 401, message = "Not authorized"), @ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response LogOut() throws SQLException {
		return Response.ok().build();
	}

	@POST
	@Path("/signup")
	@ApiOperation(value = "Return OK", notes = "Return OK if user is created", response = UserDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User was successfuly created ", response = UserDTO.class),
			@ApiResponse(code = 401, message = "Not authorized"),
			@ApiResponse(code = 404, message = "User was not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response AddUser(UserDTO user) throws MessagingException {
		userService.insertUser(user);
		return Response.ok().build();
	}

	@GET
	@Path("/query")
	@ApiOperation(value = "Return OK", notes = "Return OK if user is logged", response = UserDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User is logged in.", response = UserDTO.class),
			@ApiResponse(code = 401, message = "Not authorized"), @ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response checkUser(@QueryParam("userName") String userName) {
		UserDTO dbUser = userService.getUserByName(userName);
		return Response.ok().entity(dbUser).build();
	}
	
	
	@GET
	@Path("/all")
	@ApiOperation(value = "Return OK", notes = "Return all users", response = UserDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get all users", response = UserDTO.class),
			@ApiResponse(code = 401, message = "Not authorized"),
			@ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response allUsers() throws SQLException {
		List<UserDTO> users = userService.getAllUsers();
		LOGGER.info("---user-up--" + users.toString());
		return Response.ok().entity(users).build();
	}
	
	@GET
	@Path("/activate/query")
	@ApiOperation(value = "Return OK", notes = "Return OK if user is logged", response = UserDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User is logged in.", response = UserDTO.class),
			@ApiResponse(code = 401, message = "Not authorized"), @ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response checkUserToken(@QueryParam("tokenId") String tokenId) {
		userService.activateUser(tokenId);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/query")
	@ApiOperation(value = "Return OK", notes = "Return OK if user is deleted", response = UserDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User is deleted.", response = UserDTO.class),
			@ApiResponse(code = 401, message = "Not authorized"), 
			@ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response deleteUser(@QueryParam("idUser") String idUser, @QueryParam("roleId") String roleId) {
		 userService.deleteUser(idUser, roleId);
		return Response.ok().build();
	}
	
	
	@PUT
	@Path("/edit")
	@ApiOperation(value = "Edit user", notes = "Edit a user.")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "User was successfuly edited "),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response update(UserDTO user) {
		userService.updateUserDetails(user);
		return Response.ok().entity(user).build();
	}
	
	@GET
	@Path("/reset/query")
	@ApiOperation(value = "Return OK", notes = "Return OK if the email is sent", response = UserDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The mail is sent.", response = UserDTO.class),
			@ApiResponse(code = 401, message = "Not authorized"), @ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response resetPasswordRequest(@QueryParam("userName") String userName, @QueryParam("email") String email) {
		userService.resetPasswordRequest(userName, email);
		return Response.ok().build();
	}
	
	@GET
	@Path("/token/query")
	@ApiOperation(value = "Return OK", notes = "Return OK if the token is find", response = TokenDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "The find is find.", response = TokenDTO.class),
			@ApiResponse(code = 401, message = "Not authorized"), @ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response resetPasswordRequest(@QueryParam("tokenId") String tokenId) {
		TokenDTO token = tokenService.getById(tokenId);
		LOGGER.info(tokenId);
		return Response.ok().entity(token).build();
	}
	
	@PUT
	@Path("/reset")
	@ApiOperation(value = "Edit user password", notes = "Edit a user password.")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Password was successfuly edited "),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response updatePassword(TokenDTO token) {
		userService.updatePassword(token);
		return Response.ok().build();
	}
	  
	
	private String encodeCredentialsInBase64 (String username , String password){
		String encodedUser = username + ":" + password;
		byte[] byteUserToken = org.springframework.security.crypto.codec.Base64
				.encode(encodedUser.getBytes(Charset.forName("UTF-8")));
		return  "Basic " + new String(byteUserToken, Charset.forName("UTF-8"));
	}
	
}
