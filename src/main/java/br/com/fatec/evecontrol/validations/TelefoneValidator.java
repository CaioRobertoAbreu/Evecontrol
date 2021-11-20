package br.com.fatec.evecontrol.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TelefoneValidator implements ConstraintValidator<Telefone, String> {

    private static final String REGEX_FORMATO_TELEFONE = "\\(\\d{2}\\)(\\d{9}|\\d{8})";
    private static final String REGEX_DIGITOS_REP = "(\\d+)\\1+";
    private static final String REGEX_DDD_VALIDO = "[1-9]{2}";

    @Override
    public void initialize(Telefone constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String telefone, ConstraintValidatorContext constraintValidatorContext) {

        if(!telefone.matches(REGEX_FORMATO_TELEFONE)){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Formato de telefone inválido")
                    .addConstraintViolation();


            return false;
        }

        var ddd = telefone.substring(1, 3);
        var telefoneSemDDD = telefone.substring(4);

        if(telefoneSemDDD.matches(REGEX_DIGITOS_REP) || !ddd.matches(REGEX_DDD_VALIDO)){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Numero de telefone inválido")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

}
