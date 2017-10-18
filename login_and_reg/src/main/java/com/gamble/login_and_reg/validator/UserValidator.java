package com.gamble.login_and_reg.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.gamble.login_and_reg.models.User;

@Component
public class UserValidator implements Validator {
    
	//Specifies that an instance of the User Domain Model can be validated with this custom validator
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    //Creating our custom validation. We can add errors via rejectValue
    @Override
    public void validate(Object object, Errors errors) {
        User user = (User) object;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirmation", "Match");
        }         
    }

}
