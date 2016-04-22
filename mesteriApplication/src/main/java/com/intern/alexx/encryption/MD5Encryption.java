package com.intern.alexx.encryption;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;

import org.springframework.stereotype.Component; 

@Component
public class MD5Encryption implements Encryption {

	@Override
	public String  encrypt(String content) throws GeneralSecurityException {
		 // Create MessageDigest instance for MD5
		MessageDigest md = MessageDigest.getInstance("MD5");
		 //Add password bytes to digest
        md.update(content.getBytes());
      //Get the hash's bytes 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<byteData.length;i++) {
    		String hex=Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    	 
        return hexString.toString(); 
	}
	

}
