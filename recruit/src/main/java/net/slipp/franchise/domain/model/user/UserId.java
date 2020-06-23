package net.slipp.franchise.domain.model.user;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.stream.Collectors;

import static net.slipp.common.Assertions.assertStateTrue;

public class UserId {

    @Email
    @NotBlank
    private final String email;

    public static UserId of(String email) {
        return new UserId(email);
    }

    UserId(String email) {
        this.email = StringUtils.trim(email);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<UserId>> violations = validator.validate(this);
        assertStateTrue(violations.isEmpty(), violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining()));
    }

    @Override
    public String toString() {
        return String.valueOf(email);
    }
}
