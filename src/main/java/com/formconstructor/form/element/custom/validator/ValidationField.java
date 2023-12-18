package com.formconstructor.form.element.custom.validator;

import java.util.List;

public interface ValidationField {

    void validate();

    boolean isValidated();

    List<Validator> getValidators();

    ValidationField addValidator(Validator validator);
}