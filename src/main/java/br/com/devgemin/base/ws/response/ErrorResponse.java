package br.com.devgemin.base.ws.response;

import io.swagger.annotations.ApiModel;

@ApiModel("errorModel")
public class ErrorResponse implements Comparable<ErrorResponse> {

	private String code;
	private String message;
	private String detail;

	public ErrorResponse() {
	}

	public ErrorResponse(String code, String message, String detail) {
		this.code = code;
		this.message = message;
		this.detail = detail;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getDetail() {
		return detail;
	}

	@Override
	public int compareTo(ErrorResponse o) {
		return this.message.compareTo(o.getMessage());
	}

	@Override
	public String toString() {
		return "code: " + getCode() + " message: " + getMessage();
	}
}