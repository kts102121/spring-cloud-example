package org.ron.userservice.exception.model;

import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorResponse {
	// General Error message
	private final String message;

	private final Date timestamp;

	public ErrorResponse(final String message) {
		this.message = message;
		this.timestamp = new Date();
	}
}