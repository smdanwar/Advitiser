package com.example.advertiser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AdvertiserAlreadyExistedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdvertiserAlreadyExistedException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdvertiserAlreadyExistedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public AdvertiserAlreadyExistedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AdvertiserAlreadyExistedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AdvertiserAlreadyExistedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	

}
