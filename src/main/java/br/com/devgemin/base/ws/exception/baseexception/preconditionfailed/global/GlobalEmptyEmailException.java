package br.com.devgemin.base.ws.exception.baseexception.preconditionfailed.global;

import static br.com.devgemin.base.ws.message.MessageKey.GLOBAL_EMPTY_EMAIL;

import br.com.devgemin.base.ws.exception.baseexception.PreconditionFailedException;

public class GlobalEmptyEmailException extends PreconditionFailedException {

    private static final long serialVersionUID = 1L;

    public GlobalEmptyEmailException() {
        super(GLOBAL_EMPTY_EMAIL);
    }
}