package br.com.fatec.evecontrol.validations;

import br.com.fatec.evecontrol.repository.DonoEventoRepository;
import lombok.AllArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class CPFUnicoValidator implements ConstraintValidator<CPFUnico, String> {

    private final DonoEventoRepository repository;

    @Override
    public void initialize(CPFUnico constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext constraintValidatorContext) {

        return !repository.existsByCpf(cpf);
    }
}
