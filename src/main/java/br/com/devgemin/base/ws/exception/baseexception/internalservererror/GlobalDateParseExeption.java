package br.com.devgemin.base.ws.exception.baseexception.internalservererror;

import static br.com.devgemin.base.ws.message.MessageKey.GLOBAL_DATE_PARSE;

import br.com.devgemin.base.ws.exception.baseexception.InternalServerErrorException;

public class GlobalDateParseExeption extends InternalServerErrorException {

	private static final long serialVersionUID = 1L;

	public GlobalDateParseExeption(Exception e) {
		super(e, GLOBAL_DATE_PARSE);
	}

	public GlobalDateParseExeption() {
		super(null, GLOBAL_DATE_PARSE);
	}
}