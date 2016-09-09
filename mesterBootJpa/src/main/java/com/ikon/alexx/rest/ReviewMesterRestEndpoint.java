package com.ikon.alexx.rest;

import java.sql.SQLException;

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

import com.ikon.alexx.model.FullReview;
import com.ikon.alexx.model.MyPage;
import com.ikon.alexx.model.ReviewDTO;
import com.ikon.alexx.service.ReviewService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Api(value = "/review", description = "Endpoint for review listing")
@Component
@Path("/review")
@Produces(MediaType.APPLICATION_JSON)
public class ReviewMesterRestEndpoint {

	@Autowired
	private ReviewService reviewService;

	@GET
	@Path("/query")
	@ApiOperation(value = "Get review by Id ", notes = "Get review.", response = ReviewDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Review was successfully retrieved.", response = ReviewDTO.class),
			@ApiResponse(code = 404, message = "Review was not found."),
			@ApiResponse(code = 500, message = "Internal server error.") })
	public Response findByID(@QueryParam("idReview") String idReview) {
		ReviewDTO review = reviewService.getById(idReview);
		int status = review == null ? 404 : 200;
		return Response.ok(status).entity(review).build();
	}

	@POST
	@ApiOperation(value = "Save a review", notes = "Save review", response = ReviewDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Review was successfuly created.", response = ReviewDTO.class),
			@ApiResponse(code = 500, message = "Internal server error.") })
	public Response addReview(ReviewDTO review) {
		reviewService.insertReviewMester(review);
		return Response.ok(Status.CREATED).entity(review).build();
	}

	@PUT
	@ApiOperation(value = "Update a review", notes = "Update review", response = ReviewDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Review was successfuly updated.", response = ReviewDTO.class),
			@ApiResponse(code = 500, message = "Internal server error.") })
	public Response update(ReviewDTO review) {
		reviewService.updateReviewMester(review);
		return Response.ok(Status.OK).entity(review).build();
	}

	@DELETE
	@Path("/query")
	@ApiOperation(value = "Delete a review by id", notes = "Delete review by id", response = ReviewDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Review was successfuly deleted.", response = ReviewDTO.class),
			@ApiResponse(code = 500, message = "Internal server error.") })
	public Response delete(@QueryParam("idReview") String idReview) {
		reviewService.deleteReviewMester(idReview);
		return Response.ok().build();
	}

	@GET
	@Path("/mester/query")
	@ApiOperation(value = "Get the  mester review page", notes = "Get the page.", response = ReviewDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Review page was successfully retrived.", response = ReviewDTO.class),
			@ApiResponse(code = 404, message = "Review page not found."),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getReviewForMesterPage(@QueryParam("idMester") String idMester,
			@QueryParam("pageSize") Integer pageSize, @QueryParam("pageNumber") Integer pageNumber)
			throws SQLException {
		MyPage<ReviewDTO> reviewPage = reviewService.getReviewMasterPage(idMester, pageSize, pageNumber);
		return Response.ok(Status.OK).entity(reviewPage).build();
	}

	@GET
	@Path("/client/query")
	@ApiOperation(value = "Get the  mester review page", notes = "Get the page.", response = ReviewDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Review page was successfully retrived.", response = ReviewDTO.class),
			@ApiResponse(code = 404, message = "Review page not found."),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getReviewFromClientPage(@QueryParam("idClient") String idClient,
			@QueryParam("pageSize") Integer pageSize, @QueryParam("pageNumber") Integer pageNumber)
			throws SQLException {
		MyPage<ReviewDTO> reviewPage = reviewService.getAllReviewFromClient(idClient, pageSize, pageNumber);
		return Response.ok(Status.OK).entity(reviewPage).build();
	}

	@GET
	@Path("/getAll/query")
	@ApiOperation(value = "Get the review page", notes = "Get the page.", response = ReviewDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Review page was successfully retrived.", response = ReviewDTO.class),
			@ApiResponse(code = 404, message = "Review page not found."),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getReviewPage(@QueryParam("pageSize") Integer pageSize,
			@QueryParam("pageNumber") Integer pageNumber) throws SQLException {
		MyPage<ReviewDTO> reviewPage = reviewService.getReviewAllMasterPage(pageSize, pageNumber);
		return Response.ok(Status.OK).entity(reviewPage).build();
	}

	@GET
	@Path("/full/client/query")
	@ApiOperation(value = "Get the mester review page", notes = "Get thepage.", response = FullReview.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Review page was successfullyretrived.", response = FullReview.class),
			@ApiResponse(code = 404, message = "Review page not found."),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getFullReviewsFromClientPage(@QueryParam("idClient") String idClient,
			@QueryParam("pageSize") Integer pageSize, @QueryParam("pageNumber") Integer pageNumber)
			throws SQLException {
		MyPage<FullReview> reviewPage = reviewService.getAllFullReviewsFromClient(idClient, pageSize, pageNumber);
		return Response.ok(Status.OK).entity(reviewPage).build();
	}

	@GET
	@Path("/full/getAll/query")
	@ApiOperation(value = "Get the review page", notes = "Get the page.", response = FullReview.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Review page was successfullyretrived.", response = FullReview.class),
			@ApiResponse(code = 404, message = "Review page not found."),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getFullReviewsPage(@QueryParam("pageSize") Integer pageSize,
			@QueryParam("pageNumber") Integer pageNumber) throws SQLException {
		MyPage<FullReview> reviewPage = reviewService.getAllFullReviewsPage(pageSize, pageNumber);
		return Response.ok(Status.OK).entity(reviewPage).build();
	}
}
