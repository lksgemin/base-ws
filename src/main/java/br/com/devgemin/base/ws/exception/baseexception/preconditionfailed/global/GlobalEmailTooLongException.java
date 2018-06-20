package br.com.devgemin.base.ws.exception.baseexception.preconditionfailed.global;

import static br.com.devgemin.base.ws.message.MessageKey.GLOBAL_EMAIL_TOO_LONG;
import static java.lang.String.valueOf;

import br.com.devgemin.base.ws.exception.baseexception.PreconditionFailedException;

public class GlobalEmailTooLongException extends PreconditionFailedException {

    private static final long serialVersionUID = 1L;

    public GlobalEmailTooLongException(int maximo) {
        super(GLOBAL_EMAIL_TOO_LONG, valueOf(maximo));
    }
}