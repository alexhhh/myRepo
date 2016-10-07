package com.ikon.alexx;

import java.util.Map;

import com.ikon.alexx.exceptions.RecoverableException;

public interface MessageHandler {

	 void handleMessage(Map<String, Object> headers, byte[] body) throws RecoverableException;
}
