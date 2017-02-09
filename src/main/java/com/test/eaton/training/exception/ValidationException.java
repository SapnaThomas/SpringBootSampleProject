package com.test.eaton.training.exception;

public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;

	private int status = 400;

	private String processStatus;

	public ValidationException(String code, String message, int status,
			Throwable cause) {
		super(message, cause);
		this.code = code;
		this.status = status;
	}

	public ValidationException(String code, String message, int status,
			String processStatus, Throwable cause) {
		super(message, cause);
		this.code = code;
		this.status = status;
		this.processStatus = processStatus;
	}

	/**
	 * Return the error code associated with this exception. Error codes are
	 * dotted notation string codes (e.g. out.of.stock).
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Return the HTTP status code associated with this exception. This status
	 * code can be used as a hint to the REST API layer.
	 */
	public int getStatus() {
		return status;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}
	

}
