package br.com.devgemin.base.ws.service.user.update;

import static br.com.devgemin.base.ws.message.MessageKey.UPDATE_USER_SUCCESS;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.devgemin.base.ws.exception.baseexception.preconditionfailed.global.UpdateUserBlockedException;
import br.com.devgemin.base.ws.model.User;
import br.com.devgemin.base.ws.repository.UserRepository;
import br.com.devgemin.base.ws.request.UpdateUserRequest;
import br.com.devgemin.base.ws.response.SuccessResponse;
import br.com.devgemin.base.ws.util.I18N;
import br.com.devgemin.base.ws.util.Validator;

@Component
public class UpdateUserService {

	private UserRepository userRepository;
	
	private UpdateUserBuilder updateUserBuilder;
	
	private Validator validator;
	
	private I18N i18n;

	public UpdateUserService(UserRepository userRepository, UpdateUserBuilder updateUserBuilder,  Validator validator, I18N i18n) {
		this.userRepository = userRepository;
		this.updateUserBuilder = updateUserBuilder;
		this.validator = validator;
		this.i18n = i18n;
	}
	
	public SuccessResponse update(String idUser, UpdateUserRequest request) {
		
		validator.idUserEmpty(idUser);
		validator.idUserInvalid(idUser);

		User user = userRepository.findById(Long.valueOf(idUser)).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + idUser)
        );

        validateActiveUser(user);

        User changedUser = updateUserBuilder.build(user, request);

        userRepository.save(changedUser);

        return newSuccessResponse();
    }
	
	private void validateActiveUser(User user) {
        if (!user.isActive())
            throw new UpdateUserBlockedException();
    }
	
   private SuccessResponse newSuccessResponse() {
        return new SuccessResponse(UPDATE_USER_SUCCESS.getCode(), i18n.get(UPDATE_USER_SUCCESS));
    }

	
}
