package com.intern.alexx.services;

 
import com.intern.alexx.model.User;

 
public interface UserService {

	
	public User getUser(String userName, String password);
	
	public User getUserByName(String userName ) ;
	
	public void insertUser(User user) throws Exception ;
	
	public void activateUser(String tokenId);
 
}
