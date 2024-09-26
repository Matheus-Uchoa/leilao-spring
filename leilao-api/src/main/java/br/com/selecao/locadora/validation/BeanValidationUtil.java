package br.com.selecao.locadora.validation;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class BeanValidationUtil {

    public static Map<String, String> getValidationErrors(BindingResult result) {
        return result.getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,   // Nome do campo
                        fieldError -> Objects.requireNonNullElse(fieldError.getDefaultMessage(), "Erro de validação")  // Mensagem de erro com tratamento para null
                ));
    }
}
