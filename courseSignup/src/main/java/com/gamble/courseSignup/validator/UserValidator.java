package com.gamble.courseSignup.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.gamble.courseSignup.models.User;

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
        
        if (!user.getConf_password().equals(user.getPassword())) {
            errors.rejectValue("conf_password", "Match");
        }         
    }

}
