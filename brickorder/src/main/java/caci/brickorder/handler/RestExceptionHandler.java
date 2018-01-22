package caci.brickorder.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import caci.brickorder.exception.AlreadyShippedOrderException;
import caci.brickorder.exception.InvalidOrderReferenceException;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler  {
	
	@ExceptionHandler(AlreadyShippedOrderException.class)
	public ResponseEntity<?> handleAlreadyShippedOrderException(AlreadyShippedOrderException exc, HttpServletRequest request) {	
		return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
	}	

	@ExceptionHandler(InvalidOrderReferenceException.class)
	public ResponseEntity<?> handleInvalidOrderReferenceException(InvalidOrderReferenceException exc, HttpServletRequest request) {	
		return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
	}
}
