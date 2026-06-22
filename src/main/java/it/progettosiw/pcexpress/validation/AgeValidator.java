package it.progettosiw.pcexpress.validation;

import java.time.LocalDate;
import java.time.Period;

import it.progettosiw.pcexpress.dto.RegistrationForm;
import it.progettosiw.pcexpress.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class AgeValidator implements Validator {

    @Override
    public void validate(Object o, Errors errors) {
        LocalDate data;

        if(o instanceof RegistrationForm form)
            data = form.getDateOfBirth();
        else if (o instanceof User user)
            data = user.getDateOfBirth();
        else
            return;

        if (data != null) {
            int age = Period.between(data, LocalDate.now()).getYears();
            if (age < 18)
                errors.rejectValue("dateOfBirth", "user.tooYoung");
            if (age > 130)
                errors.rejectValue("dateOfBirth", "user.tooOld");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationForm.class.equals(aClass) || User.class.equals(aClass);
    }
}

