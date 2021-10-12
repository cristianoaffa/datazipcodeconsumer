package com.freedom.datazipcodeconsumer.exception;

public class ZipCodeInvalidException extends DataZipCodeConsumerException {

	private static final long serialVersionUID = 8335670457282022856L;
	
	public ZipCodeInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public ZipCodeInvalidException(String message) {
		super(message);
	}

	public ZipCodeInvalidException(Throwable cause) {
		super(cause);
	}

}
