package br.com.devgemin.base.ws.controller;


import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.devgemin.base.ws.exception.baseexception.InternalServerErrorException;
import br.com.devgemin.base.ws.exception.baseexception.NotFoundException;
import br.com.devgemin.base.ws.exception.baseexception.PreconditionFailedException;
import br.com.devgemin.base.ws.exception.baseexception.UnauthorizedException;
import br.com.devgemin.base.ws.log.LoggerSupport;
import br.com.devgemin.base.ws.message.JSONMessage;
import br.com.devgemin.base.ws.response.ErrorResponse;



public abstract class RestService {

	@Autowired
	private JSONMessage jsonMessage;
	@Autowired
	protected LoggerSupport loggerSupport;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(PRECONDITION_FAILED)
	public @ResponseBody ErrorResponse handleException(HttpServletRequest request, MethodArgumentNotValidException e) {
		log(request, e);

		return jsonMessage.getErrorResponse(e);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(PRECONDITION_FAILED)
	public @ResponseBody ErrorResponse handleException(HttpServletRequest request, IllegalArgumentException e) {
		log(request, e);

		return jsonMessage.getErrorResponse(e);
	}

	@ExceptionHandler(BindException.class)
	@ResponseStatus(PRECONDITION_FAILED)
	public @ResponseBody ErrorResponse handleException(HttpServletRequest request, BindException e) {
		log(request, e);

		return jsonMessage.getErrorResponse(e);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(PRECONDITION_FAILED)
	public @ResponseBody ErrorResponse handleException(HttpServletRequest request, HttpMessageNotReadableException e) {
		log(request, e);

		return jsonMessage.getErrorResponse(e);
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(NOT_FOUND)
	public @ResponseBody ErrorResponse handleException(HttpServletRequest request, NotFoundException e) {
		log(request, e);

		return jsonMessage.getErrorResponse(e);
	}

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(UNAUTHORIZED)
	public @ResponseBody ErrorResponse handleException(HttpServletRequest request, UnauthorizedException e) {
		log(request, e);

		return jsonMessage.getErrorResponse(e);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(UNAUTHORIZED)
	public @ResponseBody ErrorResponse handleException(HttpServletRequest request, BadCredentialsException e) {
		log(request, e);

		return jsonMessage.getErrorResponse(e);
	}
	

	@ExceptionHandler(PreconditionFailedException.class)
	@ResponseStatus(PRECONDITION_FAILED)
	public @ResponseBody ErrorResponse handleException(HttpServletRequest request, PreconditionFailedException e) {
		log(request, e);

		return jsonMessage.getErrorResponse(e);
	}

	@ExceptionHandler(InternalServerErrorException.class)
	@ResponseStatus(INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponse handleException(HttpServletRequest request, InternalServerErrorException e) {
		log(request, e);

		return jsonMessage.getErrorResponse(e);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponse handleException(HttpServletRequest request, Exception e) {
		log(request, e);

		return jsonMessage.getErrorResponse(e);
	}

	private void log(HttpServletRequest request, Exception e) {
		loggerSupport.logError(e.getMessage(), e);
		loggerSupport.logError(request);
	}
}