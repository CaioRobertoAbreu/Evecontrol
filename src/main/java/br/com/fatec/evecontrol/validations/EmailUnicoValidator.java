package br.com.fatec.evecontrol.validations;

import br.com.fatec.evecontrol.repository.DonoEventoRepository;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class EmailUnicoValidator implements ConstraintValidator<EmailUnico, String> {

    private final DonoEventoRepository repository;

    @Override
    public void initialize(EmailUnico constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {

        return !repository.existsByEmail(email);
    }
}
