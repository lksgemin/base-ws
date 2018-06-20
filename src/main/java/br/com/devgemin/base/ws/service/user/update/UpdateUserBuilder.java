package br.com.devgemin.base.ws.service.user.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.devgemin.base.ws.model.User;
import br.com.devgemin.base.ws.request.UpdateUserRequest;
import br.com.devgemin.base.ws.util.Validator;

@Component
public class UpdateUserBuilder {

    private Validator validator;

    @Autowired
    public UpdateUserBuilder(Validator validator) {
        this.validator = validator;
    }

    public User build(User user, UpdateUserRequest request) {
        validate(user, request);

        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.getRoles().addAll(request.getRoles());
        
        return user;
    }

    private void validate(User user, UpdateUserRequest request) {
        validator.emptyName(request.getName());
        validator.nameTooLong(request.getName());
    }
}