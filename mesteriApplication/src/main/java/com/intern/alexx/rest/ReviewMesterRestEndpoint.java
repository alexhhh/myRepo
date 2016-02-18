package com.intern.alexx.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.intern.alexx.model.MesterSearchCriteria;
import com.intern.alexx.model.MyPage;
import com.intern.alexx.model.ReviewMester;
import com.intern.alexx.services.ReviewMesterService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponses;
import com.wordnik.swagger.annotations.ApiResponse;

@Api(value = "/reviews", description = "Endpoint for review listing")
@Component
@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
public class ReviewMesterRestEndpoint {

	@Autowired
	private ReviewMesterService reviewService;

	@GET
	@Path("{id}")
	@ApiOperation(value = "Get review by Id ", notes = "Get review.", response = ReviewMester.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Review was successfully retrieved.", response = ReviewMester.class),
			@ApiResponse(code = 404, message = "Review was not found."),
			@ApiResponse(code = 500, message = "Internal server error.") })
	public Response findByID(@PathParam("id") Integer id) {
		ReviewMester review = new ReviewMester();
		review.setId(id);
		reviewService.getById(review);
		int status = review == null ? 404 : 200;
		return Response.ok(status).entity(review).build();
	}

	@GET
	@ApiOperation(value = "Get the review page", notes = "Get the page.", response = ReviewMester.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Review page was successfully retrived.", response = ReviewMester.class),
			@ApiResponse(code = 404, message = "Review page not found."),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getReviewPage(MesterSearchCriteria searchCriteria) {
		MyPage<ReviewMester> reviewPage = reviewService.getReviewAllMasterPage(searchCriteria);
		return Response.ok(Status.OK).entity(reviewPage).build();
	}

	@GET
	@Path("{id}")
	@ApiOperation(value = "Get the  mester review page", notes = "Get the page.", response = ReviewMester.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Review page was successfully retrived.", response = ReviewMester.class),
			@ApiResponse(code = 404, message = "Review page not found."),
			@ApiResponse(code = 500, message = "Internal server error") })
	public Response getReviewForMesterPage(Integer idMester, MesterSearchCriteria searchCriteria) {
		MyPage<ReviewMester> reviewPage = reviewService.getReviewMasterPage(idMester, searchCriteria);
		return Response.ok(Status.OK).entity(reviewPage).build();
	}

	@POST
	@ApiOperation(value = "Save a review", notes = "Save review", response = ReviewMester.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Review was successfuly saved.", response = ReviewMester.class),
			@ApiResponse(code = 500, message = "Internal server error.") })
	public Response addReview(ReviewMester review) {
		reviewService.insertReviewMester(review);
		return Response.ok(review).build();
	}

	@PUT
	@Path("{id}")
	@ApiOperation(value = "Update a review", notes = "Update review", response = ReviewMester.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Review was successfuly updated.", response = ReviewMester.class),
			@ApiResponse(code = 500, message = "Internal server error.") })
	public Response update(@PathParam("id") Integer id,ReviewMester review) {
		review.setId(id);
		reviewService.updateReviewMester(review);
		return Response.ok(Status.OK).entity(review).build();
	}

	@DELETE
	@Path("{id}")
	@ApiOperation(value = "Delete a review", notes = "Delete review", response = ReviewMester.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Review was successfuly deleted.", response = ReviewMester.class),
			@ApiResponse(code = 500, message = "Internal server error.") })
	public Response delete(@PathParam("id") Integer id) {
		ReviewMester review = new ReviewMester();
		review.setId(id);
		reviewService.deleteReviewMester(review);
		return Response.accepted().build();
	}
}
