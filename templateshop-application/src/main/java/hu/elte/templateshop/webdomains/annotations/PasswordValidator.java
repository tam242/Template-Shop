package hu.elte.templateshop.webdomains.annotations;

import hu.elte.templateshop.webdomains.LoginRequest;
import hu.elte.templateshop.webdomains.RegisterRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "required.password", "Field name is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
                "required.confirmPassword", "Field name is required.");

        RegisterRequest request = (RegisterRequest) target;

        if(!(request.getPassword().equals(request.getConfirmPassword()))){
            errors.rejectValue("password", "notmatch.password");
        }

    }
}