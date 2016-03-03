package com.intern.alexx.rest.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

 
 
import com.intern.alexx.model.ReviewMester;
import com.intern.alexx.model.ReviewMester.Price;
import com.intern.alexx.rest.ReviewMesterRestEndpoint;
import com.intern.alexx.services.ReviewMesterService;


@RunWith(MockitoJUnitRunner.class)
public class ReviewMesterEndpointTest {

	
	
	@InjectMocks
	private ReviewMesterRestEndpoint endpoint;
	
	@Mock
	private ReviewMesterService reviewService;
	
	@Mock
	private UriInfo uriInfo;
	
	
	@Test
	public void testFindOne_WhenReviewFound_ThenReviewIsReturned() {
		String id = "101";
		ReviewMester review= createReviewMester();
		Mockito.when(reviewService.getById(id)).thenReturn(review);		
		Response response = endpoint.findByID(id);
		ReviewMester theReview = (ReviewMester)response.getEntity();
		assertThat(response.getStatus(), is(200));				
		assertThat(theReview, is(review));
	} 

	
	@Test   
	public void  testSaveReview_WhenSuccessful_ThenReturnReview() throws Exception{
		
		ReviewMester review = createReviewMester();
		String mesterUri = "http://localhost:8080/mesteriApplication/rest/reviews/101";
		mockLocationHttpHeader(mesterUri);		 
		endpoint.addReview(review);
		Mockito.verify(reviewService).insertReviewMester(review);
 	
	}
	
	@Test  
	public void testUpdateReview_WhenSuccessful_ThenUpdateReview(){
		String id = "101";
		ReviewMester review= createReviewMester();	
		Response response= endpoint.update(id, review);
		assertThat(isSuccessful(response), is(true));
		Mockito.verify(reviewService).updateReviewMester(review);
	}
	
	@Test
	public void testDeletereviw_WhenSuccessful_ThenDeleteReview(){
		String id = "101";
		Response response=endpoint.delete(id);
		assertThat(isSuccessful(response), is(true));	 
		Mockito.verify(reviewService).deleteReviewMester( id);
 	
	}
	
	
	
	
	private void mockLocationHttpHeader(String resourceId) throws Exception {
		UriBuilder mockUriBuilder = Mockito.mock(UriBuilder.class);
		Mockito.when(uriInfo.getAbsolutePathBuilder()).thenReturn(mockUriBuilder);
		Mockito.when(mockUriBuilder.path(Mockito.anyString())).thenReturn(mockUriBuilder);
		Mockito.when(mockUriBuilder.resolveTemplate(Mockito.anyString(), Mockito.anyObject())).thenReturn(mockUriBuilder);
		Mockito.when(mockUriBuilder.build()).thenReturn(new URI(resourceId));
	}
	
	private boolean isSuccessful(Response response){
		return Status.Family.SUCCESSFUL.equals(response.getStatusInfo().getFamily());
	}
	
	
	private ReviewMester createReviewMester() {
		ReviewMester reviewMester = new ReviewMester();
		reviewMester.setId("55");
		reviewMester.setIdMester("8");
		reviewMester.setIdClient("5");
		reviewMester.setPrice(Price.HIGH);
		reviewMester.setRating(5);
		reviewMester.setFeedback("Awesome Update");
		return reviewMester;
	}

}
