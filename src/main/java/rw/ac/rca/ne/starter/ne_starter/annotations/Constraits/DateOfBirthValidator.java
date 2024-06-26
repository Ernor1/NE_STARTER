package rw.ac.rca.ne.starter.ne_starter.annotations.Constraits;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateOfBirthValidator implements ConstraintValidator<ValidDateOfBirth, LocalDate> {
    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
        return dateOfBirth == null || !dateOfBirth.isAfter(LocalDate.now());
    }
}

