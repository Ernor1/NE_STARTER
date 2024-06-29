package rw.ac.rca.ne.starter.ne_starter.annotations.Constraits;

import rw.ac.rca.ne.starter.ne_starter.annotations.ValidGender;
import rw.ac.rca.ne.starter.ne_starter.enums.EGender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<ValidGender, EGender> {

    @Override
    public void initialize(ValidGender constraintAnnotation) {
    }

    @Override
    public boolean isValid(EGender gender, ConstraintValidatorContext context) {
        if (gender == null) {
            return false;
        }

        // Check if the gender is either MALE or FEMALE
        return gender == EGender.MALE || gender == EGender.FEMALE;
    }
}
