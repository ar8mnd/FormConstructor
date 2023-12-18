package com.formconstructor.form.element.custom.validator;

public class RegexValidator extends Validator {

    private final String regex;

    public RegexValidator(String name, String regex) {
        super(name);
        this.regex = regex;
    }

    @Override
    public void validate(String input) {
        this.validated = input.matches(regex);
    }
}