package com.intern.alexx.repository.impl;

import java.util.UUID;

public class GUIDGenerator {

	public static String generatedID() {
		return UUID.randomUUID().toString();
	}
}
