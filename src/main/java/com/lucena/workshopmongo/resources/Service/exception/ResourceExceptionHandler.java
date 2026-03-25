package com.lucena.workshopmongo.resources.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lucena.workshopmongo.resources.exception.StandarError;

import jakarta.servlet.http.HttpServletRequest;

/**
 * esse @ indica que essa classe é responsave por tratar possiveis erros nas
 * requisições
 */
@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandarError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;

		StandarError err = new StandarError(
				System.currentTimeMillis(), 
				status.value(), 
				"não encontrado",
				e.getMessage(), 
				request.getRequestURI());

			return ResponseEntity.status(status).body(err);
	}
}
