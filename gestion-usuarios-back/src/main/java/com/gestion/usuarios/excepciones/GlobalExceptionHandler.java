package com.gestion.usuarios.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import com.gestion.usuarios.util.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	//
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
		//
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), null, ex.getMessage()));
	}

	//
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ApiResponse<String>> handleResponseStatusException(ResponseStatusException ex) {
		//
		HttpStatus status = (HttpStatus) ex.getStatusCode();
		//
		String reason = ex.getMessage();

		//
		return ResponseEntity.status(status).body(new ApiResponse<>(status.value(), null, reason));
	}

	//
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<String>> handleGlobalException(Exception ex) {
		//
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Error interno del servidor"));
	}
}
