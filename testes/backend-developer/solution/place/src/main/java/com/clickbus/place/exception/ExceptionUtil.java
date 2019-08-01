package com.clickbus.place.exception;

import javax.validation.ConstraintViolationException;

public class ExceptionUtil {

	
	public static String parseConstraintExceptionMessage(ConstraintViolationException cv) {
		return cv.getConstraintViolations()
				.stream()
				.map(ve -> ve.getMessage())
				.reduce( (acc, m)-> {
					acc += m.concat(" ");
					return acc;
				})
				.get();
	}
	
	public static ConstraintViolationException searchForConstraintException(Exception e) {
		Throwable cause = e;
		while (cause != null &&  !ConstraintViolationException.class.isInstance(cause)) {
			cause = cause.getCause();
		}
		
		return (ConstraintViolationException)cause;
	}
}
