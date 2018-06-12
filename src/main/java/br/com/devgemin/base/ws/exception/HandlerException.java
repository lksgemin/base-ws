package br.com.devgemin.base.ws.exception;

import static org.apache.commons.lang3.StringUtils.isBlank;
import br.com.devgemin.base.ws.message.MessageKey;

public class HandlerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String detail;
	private MessageKey messageKey;
	private String[] param;
	private String code;
	private String message;

	public HandlerException(MessageKey messageKey, String[] param) {
		this.messageKey = messageKey;
		this.param = param;
	}

	public HandlerException(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getDetail() {
		return detail;
	}

	public MessageKey getMessageKey() {
		return messageKey;
	}

	public String[] getParam() {
		return param;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return !isBlank(this.message) ? this.message : super.getMessage();
	}
}