package br.com.devgemin.base.ws.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;

import br.com.devgemin.base.ws.exception.HandlerException;
import br.com.devgemin.base.ws.exception.baseexception.InternalServerErrorException;
import br.com.devgemin.base.ws.response.ErrorResponse;
import br.com.devgemin.base.ws.util.I18N;

@Component
public class JSONMessage {

	private I18N i18n;

	@Autowired
	public JSONMessage(I18N i18n) {
		this.i18n = i18n;
	}

	public ErrorResponse getErrorResponse(IllegalArgumentException e) {
		MessageKey messageKey = MessageKey.GLOBAL_PATH_PARAMETER_INVALID;
		return new ErrorResponse(messageKey.getCode(), i18n.get(messageKey), null);
	}

	public ErrorResponse getErrorResponse(HttpMessageNotReadableException e) {
		MessageKey messageKey = MessageKey.GLOBAL_REQUEST_JSON_INVALID;
		return new ErrorResponse(messageKey.getCode(), i18n.get(messageKey), null);
	}

	public ErrorResponse getErrorResponse(HandlerException e) {
		return new ErrorResponse(getCode(e), getMessage(e), e.getDetail());
	}

	public ErrorResponse getErrorResponse(InternalServerErrorException e) {
		return new ErrorResponse(getCode(e), getMessage(e) + getMessage(e.getException()), e.getDetail());
	}

	public ErrorResponse getErrorResponse(Exception e) {
		MessageKey messageKey = MessageKey.GLOBAL_UNEXPECTED_ERROR;
		return new ErrorResponse(messageKey.getCode(), i18n.get(messageKey) + getMessage(e), null);
	}

	private String getCode(HandlerException e) {
		return e.getMessageKey() == null ? e.getCode() : e.getMessageKey().getCode();
	}

	private String getMessage(Exception e) {
		if (e == null)
			return "";

		if (e.getMessage() == null)
			return ": " + e.getClass().getSimpleName();

		return ": " + e.getMessage();
	}

	private String getMessage(HandlerException e) {
		boolean notMessageKey = e.getMessageKey() == null;
		return notMessageKey ? e.getMessage() : i18n.get(e.getMessageKey(), e.getParam());
	}
}