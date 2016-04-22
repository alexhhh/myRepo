package com.intern.alexx.encryption;

import java.security.GeneralSecurityException;

public interface Encryption {
	public String  encrypt(String content) throws GeneralSecurityException;
}
