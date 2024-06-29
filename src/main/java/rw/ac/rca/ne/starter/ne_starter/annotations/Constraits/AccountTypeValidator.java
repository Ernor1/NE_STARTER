package rw.ac.rca.ne.starter.ne_starter.annotations.Constraits;

import rw.ac.rca.ne.starter.ne_starter.annotations.ValidAccountType;
import rw.ac.rca.ne.starter.ne_starter.annotations.ValidGender;
import rw.ac.rca.ne.starter.ne_starter.enums.EAccountType;
import rw.ac.rca.ne.starter.ne_starter.enums.EGender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountTypeValidator implements ConstraintValidator<ValidAccountType, EAccountType> {

    @Override
    public void initialize(ValidAccountType constraintAnnotation) {
    }

    @Override
    public boolean isValid(EAccountType accountType, ConstraintValidatorContext context) {
        if (accountType == null) {
            return false;
        }

        // Check if the accountType is either MALE or FEMALE
        return accountType == EAccountType.CURRENT || accountType == EAccountType.SAVINGS || accountType==EAccountType.FIXED_DEPOSIT;
    }
}
