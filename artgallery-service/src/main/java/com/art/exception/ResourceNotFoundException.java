package com.art.exception;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException(String mesg) {
		super(mesg);
	}

}
