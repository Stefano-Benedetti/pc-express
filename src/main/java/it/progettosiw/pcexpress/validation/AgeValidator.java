package it.progettosiw.pcexpress.validation;

import java.time.LocalDate;
import java.time.Period;

import it.progettosiw.pcexpress.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class AgeValidator implements Validator {

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User)o;
        if (user.getDateOfBirth()!=null) {
            int age = Period.between(user.getDateOfBirth(), LocalDate.now()).getYears();
            if (age < 18) {
                errors.rejectValue("dateOfBirth", "user.tooYoung");
            }
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }
}

