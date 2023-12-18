package ru.contentforge.formconstructor.form.element.custom;

import ru.contentforge.formconstructor.form.element.ElementType;
import ru.contentforge.formconstructor.form.element.custom.validator.ValidationField;
import ru.contentforge.formconstructor.form.element.custom.validator.Validator;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Input extends CustomElement implements ValidationField {

    private String placeholder;

    @SerializedName("default")
    protected String defaultValue;

    private transient String value;

    private transient boolean trim;

    private transient List<Validator> validators;

    public Input() {
        this("");
    }

    public Input(String name) {
        this(name, "");
    }

    public Input(String name, String placeholder) {
        this(name, placeholder, "");
    }

    public Input(String name, String placeholder, String defaultValue) {
        this(name, placeholder, defaultValue, false, new ArrayList<>());
    }

    private Input(String name, String placeholder, String defaultValue, boolean trim, List<Validator> validators) {
        super(name, ElementType.INPUT);
        this.placeholder = placeholder;
        this.defaultValue = defaultValue;
        this.trim = trim;
        this.validators = validators;
    }

    public Input setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public Input setDefaultValue(String defaultValue) {
        this.value = defaultValue;
        return this;
    }

    public Input setTrim(boolean trim) {
        this.trim = trim;
        return this;
    }

    @Override
    public void validate() {
        for (Validator validator: validators) validator.validate(value);
    }

    @Override
    public boolean isValidated() {
        return validators.stream().allMatch(Validator::isValidated);
    }

    @Override
    public Input addValidator(Validator validator) {
        validators.add(validator);
        return this;
    }

    public Input setValidators(List<Validator> validators) {
        this.validators = validators;
        return this;
    }

    public Input addValidators(Validator... validator) {
        this.validators.addAll(Arrays.asList(validator));
        return this;
    }

    @Override
    public boolean respond(Object value) {
        this.value = trim ? ((String) value).trim() : (String) value;
        this.validate();
        return true;
    }
}