package br.com.devgemin.base.ws.message;



public enum MessageKey {

	GLOBAL_PATH_PARAMETER_INVALID(40001),
	GLOBAL_REQUEST_JSON_INVALID(40002),
	GLOBAL_UNEXPECTED_ERROR(40003),
	GLOBAL_DATE_PARSE(40004),
	
	SIGN_UP_EXISTING_USERNAME(50001),
	SIGN_UP_EXISTING_USER_EMAIL(50002),
	USER_NAME_NOT_FOUND(50003);

	private Integer code;

	private MessageKey(Integer code) {
		this.code = code;
	}

	public String getCode() {
		return String.valueOf(code);
	}
}