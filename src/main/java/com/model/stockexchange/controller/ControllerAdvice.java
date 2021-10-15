package com.model.stockexchange.controller;

import com.model.stockexchange.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(Exception.class)
	public ResponseEntity <ApiResponse> unkownException(final Exception e) {
        return unknownError(e);
    }

	private ResponseEntity < ApiResponse > unknownError(final Exception exception) {

       ApiResponse apiErrorResponse=new ApiResponse();
       apiErrorResponse.setApiError(exception.getMessage());
       HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
      return new ResponseEntity < ApiResponse> (apiErrorResponse, status);
    }

}
