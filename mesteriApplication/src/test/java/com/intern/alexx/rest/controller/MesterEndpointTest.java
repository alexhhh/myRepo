package com.intern.alexx.rest.controller;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
 

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.intern.alexx.model.Contact;
import com.intern.alexx.model.Mester;
import com.intern.alexx.model.Speciality;
import com.intern.alexx.rest.MesterRestEndpoint;
import com.intern.alexx.services.MesterService;
 
@RunWith(MockitoJUnitRunner.class)
public class MesterEndpointTest {

	
	@InjectMocks
	private MesterRestEndpoint endpoint;
	
	@Mock
	private MesterService mesterService;
	
	@Mock
	private UriInfo uriInfo;
	

	@Test
	public void testFindOne_WhenMesterFound_ThenMesterIsReturned() throws SQLException {
		String id = "101";
		Mester mester= createMester();
		Mockito.when(mesterService.getById(id)).thenReturn(mester);		
		Response response = endpoint.getById(id);
		Mester theMester = (Mester)response.getEntity();
		assertThat(response.getStatus(), is(200));				
		assertThat(theMester, is(mester));
	} 
	

	@Test
	public void testFindOneMester_WhenMesterNotFound_ThenNullIsReturned() throws SQLException {
		String id = "1010";
		Mockito.when(mesterService.getById(id)).thenReturn(null);	
		Response response = endpoint.getById(id);	
		Mester theMester = (Mester)response.getEntity();		
	//	assertThat(response.getStatus(), is(404));	 
		assertThat(theMester, is(nullValue()));
	}
	
	@Test
	public void  testSaveMester_WhenSuccessful_ThenReturnMester() throws Exception{
		Mester mester=createMester();
		String mesterUri = "http://localhost:8080/mesteriApplication/rest/mesteri/101";
		mockLocationHttpHeader(mesterUri);		 
		endpoint.insert(mester);
		Mockito.verify(mesterService).insertMester(mester);
	}
	
	
	
	@Test
	public void testDeleteMester_WhenSuccessful_ThenDeleteMester(){
		String id = "1010";
		Response response=endpoint.delete(id);
		assertThat(isSuccessful(response), is(true));	 
		Mockito.verify(mesterService).deleteMester(id);
		
	}
	
	
	@Test
	public void testUpdate_WhenSuccesfull_ThenServiceUpdatesMester(){
		 
		Mester mester= createMester();	
		Response response= endpoint.update(mester);
		assertThat(isSuccessful(response), is(true));
	//	Mockito.verify(mesterService).updateMester(mester);
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
	
	
	
	private Mester createMester() {
		Mester mester = new Mester();
		Contact contact = createContract();
		List<Speciality> speciality = createSpeciality();
		mester.setId("101");
		mester.setFirstName("Ion");
		mester.setLastName("Ionescu");
		mester.setLocation("Miami");
		mester.setDescription(" ");
		mester.setContact(contact);
		mester.setSpeciality(speciality);
		return mester;
	}

	private Contact createContract() {
		Contact contact = new Contact();
		// contact.setIdMester(666);
		contact.setEmail("mester@contact.com");
		contact.setTelNr("0479011333");
		contact.setSite("youtube");
		contact.setSocialPlatform("fb tw");
		return contact;
	}

	private List<Speciality> createSpeciality() {
		List<Speciality> spec = new ArrayList<Speciality>();
		Speciality speciality = new Speciality();
		spec.add(speciality);
		speciality.setSpecialityName("metalhead");
		return spec;
	}
}
