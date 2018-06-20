package br.com.devgemin.base.ws.exception.baseexception.preconditionfailed.global;

import static br.com.devgemin.base.ws.message.MessageKey.GLOBAL_EMPTY_NAME;

import br.com.devgemin.base.ws.exception.baseexception.PreconditionFailedException;

public class GlobalEmptyNameException extends PreconditionFailedException {

    private static final long serialVersionUID = 1L;

    public GlobalEmptyNameException() {
        super(GLOBAL_EMPTY_NAME);
    }
}