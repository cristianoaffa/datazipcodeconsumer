package com.freedom.datazipcodeconsumer.exception;

public class DataZipCodeConsumerException extends Exception {
	
	private static final long serialVersionUID = 7086881355547066729L;

	public DataZipCodeConsumerException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataZipCodeConsumerException(String message) {
		super(message);
	}

	public DataZipCodeConsumerException(Throwable cause) {
		super(cause);
	}

}
