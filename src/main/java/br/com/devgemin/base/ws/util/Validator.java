package br.com.devgemin.base.ws.util;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import br.com.devgemin.base.ws.exception.baseexception.preconditionfailed.global.GlobalEmailTooLongException;
import br.com.devgemin.base.ws.exception.baseexception.preconditionfailed.global.GlobalEmptyEmailException;
import br.com.devgemin.base.ws.exception.baseexception.preconditionfailed.global.GlobalEmptyNameException;
import br.com.devgemin.base.ws.exception.baseexception.preconditionfailed.global.GlobalIdUserEmptyException;
import br.com.devgemin.base.ws.exception.baseexception.preconditionfailed.global.GlobalIdUserInvalidException;
import br.com.devgemin.base.ws.exception.baseexception.preconditionfailed.global.GlobalInvalidEmailException;
import br.com.devgemin.base.ws.exception.baseexception.preconditionfailed.global.GlobalNameTooLongException;

@Component
public class Validator {
	public void idUserEmpty(String idUser) {
        if (isBlank(idUser))
            throw new GlobalIdUserEmptyException();
    }

    public void idUserInvalid(String idUser) {
        if (!isBlank(idUser) && !idUser.matches("\\d+"))
            throw new GlobalIdUserInvalidException();
    }
    
    public void emptyName(String name) {
        if (isBlank(name))
            throw new GlobalEmptyNameException();
    }

    public void nameTooLong(String name) {
        if (!isBlank(name) && name.length() > 100)
            throw new GlobalNameTooLongException(100);
    }

    public void emptyEmail(String email) {
        if (isBlank(email))
            throw new GlobalEmptyEmailException();
    }

    public void emailTooLong(String email) {
        if (!isBlank(email) && email.length() > 80)
            throw new GlobalEmailTooLongException(80);
    }

    public void validEmail(String email) {
        if (!isValidEmail(email))
            throw new GlobalInvalidEmailException();
    }
    
    public boolean isValidEmail(String email) {
        if (isBlank(email))
            return true;

        String emailPattern = "\\b(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{2,})|(\\.[A-Za-z0-9]{2,}\\.[A-Za-z0-9]{2,}))$)\\b";
        Pattern pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
