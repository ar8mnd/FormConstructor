package ru.contentforge.formconstructor.form.response;

import cn.nukkit.Player;
import ru.contentforge.formconstructor.form.CustomForm;
import ru.contentforge.formconstructor.form.element.custom.CustomElement;
import ru.contentforge.formconstructor.form.element.custom.Dropdown;
import ru.contentforge.formconstructor.form.element.custom.Input;
import ru.contentforge.formconstructor.form.element.custom.Label;
import ru.contentforge.formconstructor.form.element.custom.StepSlider;
import ru.contentforge.formconstructor.form.element.custom.Toggle;
import ru.contentforge.formconstructor.form.element.custom.validator.ValidationField;
import ru.contentforge.formconstructor.form.element.custom.validator.Validator;
import ru.contentforge.formconstructor.form.handler.CustomFormHandler;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomFormResponse extends FormResponse<CustomFormHandler> {

    @Getter
    protected final CustomForm form;
    protected final List<CustomElement> elements;

    public CustomFormResponse(CustomFormHandler handler, List<CustomElement> elements, CustomForm form) {
        super(handler, "");
        this.elements = elements;
        this.form = form;
    }

    public boolean containsId(String elementId) {
        return elements.stream().anyMatch(element -> elementId.equals(element.getElementId()));
    }

    public CustomElement getElement(int index) {
        return elements.get(index);
    }

    public CustomElement getElement(String elementId) {
        return elements.stream()
            .filter(element -> elementId.equals(element.getElementId()))
            .findFirst()
            .orElse(null);
    }

    public <T extends CustomElement> T getElement(String elementId, Class<T> clazz) {
        return clazz.cast(getElement(elementId));
    }

    public <T extends CustomElement> List<T> getElements(Class<T> clazz) {
        return elements.stream()
            .filter(clazz::isInstance)
            .map(clazz::cast)
            .collect(Collectors.toList());
    }

    public Label getLabel(int index) {
        return (Label) getElement(index);
    }

    public Label getLabel(String elementId) {
        return getElement(elementId, Label.class);
    }

    public List<Label> getLabels() {
        return getElements(Label.class);
    }

    public Input getInput(int index) {
        return (Input) getElement(index);
    }

    public Input getInput(String elementId) {
        return getElement(elementId, Input.class);
    }

    public List<Input> getInputs() {
        return getElements(Input.class);
    }

    public Toggle getToggle(int index) {
        return (Toggle) getElement(index);
    }

    public Toggle getToggle(String elementId) {
        return getElement(elementId, Toggle.class);
    }

    public List<Toggle> getToggles() {
        return getElements(Toggle.class);
    }

    public StepSlider getStepSlider(int index) {
        return (StepSlider) getElement(index);
    }

    public StepSlider getStepSlider(String elementId) {
        return getElement(elementId, StepSlider.class);
    }

    public List<StepSlider> getStepSliders() {
        return getElements(StepSlider.class);
    }

    public Dropdown getDropdown(int index) {
        return (Dropdown) getElement(index);
    }

    public Dropdown getDropdown(String elementId) {
        return getElement(elementId, Dropdown.class);
    }

    public List<Dropdown> getDropdowns() {
        return getElements(Dropdown.class);
    }

    @Override
    public void handle(Player player) {
        if (this.getHandler() != null) {
            this.getHandler().handle(player, this);
        }
    }
    
    public boolean isValidated() {
        return form.isValidated();
    }

    public List<String> getValidatorErrors() {
        List<String> errors = new ArrayList<>();
    
        elements.forEach(element -> {
            if (element instanceof ValidationField validationField) {
                for (Validator validator : validationField.getValidators()) {
                    if (!validator.isValidated()) {
                        errors.add(validator.getName());
                    }
                }
            }
        });

        return errors;
    }
}