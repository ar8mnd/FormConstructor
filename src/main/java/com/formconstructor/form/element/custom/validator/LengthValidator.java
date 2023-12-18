package com.formconstructor.form.element.custom.validator;

public class LengthValidator extends Validator {

    private final int min, max;

    public LengthValidator(String name, int min, int max) {
        super(name);
        this.min = min;
        this.max = max;
    }

    @Override
    public void validate(String input) {
        this.validated = (min == -1 || input.length() >= min) && (max == -1 || max >= input.length());
    }
}