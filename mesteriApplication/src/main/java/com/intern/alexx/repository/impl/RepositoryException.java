package com.intern.alexx.repository.impl;

public class RepositoryException extends RuntimeException {

	public RepositoryException() {
	}

	public RepositoryException(String message) {
		super(message);
	}

	public RepositoryException(Throwable cause) {
		super(cause);
	}

	public RepositoryException(String message, Throwable cause) {
	}
	
}
