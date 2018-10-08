package com.example.advertiser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdvertiserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdvertiserNotFoundException() {
		super();
	}

	public AdvertiserNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AdvertiserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public AdvertiserNotFoundException(String message) {
		super(message);
	}

	public AdvertiserNotFoundException(Throwable cause) {
		super(cause);
	}

}
