package com.ikon.alexx;

import java.nio.charset.Charset;
import java.util.Map;

import com.ikon.alexx.exceptions.RecoverableException;

public class DefaultMessageHandler implements MessageHandler{

	@Override
	public void handleMessage(Map<String, Object> headers, byte[] body) throws RecoverableException{
		String message = new String(body, Charset.defaultCharset());
		
		if ("recover-error".equals(message)){
			System.out.println(" [x] Received  '" + message + "' but could not process it. Throwing recovering-error");
			throw new RecoverableException("recovering exception");
		}else if ("error".equals(message)){
			System.out.println(" [x] Received  '" + message + "' but could not process it. Throwing error");
			throw new RuntimeException("my exception");
		}
		
		System.out.println(" [x] Received  '" + message + "'");
	}

}
