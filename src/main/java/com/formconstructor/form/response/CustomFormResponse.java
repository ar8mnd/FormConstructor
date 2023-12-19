package com.formconstructor.form.response;

import cn.nukkit.Player;
import com.formconstructor.form.CustomForm;
import com.formconstructor.form.element.custom.validator.ValidationField;
import com.formconstructor.form.element.custom.validator.Validator;
import com.formconstructor.form.handler.CustomFormHandler;
import com.formconstructor.form.element.custom.CustomElement;
import com.formconstructor.form.element.custom.Dropdown;
import com.formconstructor.form.element.custom.Input;
import com.formconstructor.form.element.custom.Label;
import com.formconstructor.form.element.custom.Slider;
import com.formconstructor.form.element.custom.StepSlider;
import com.formconstructor.form.element.custom.Toggle;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomFormResponse extends FormResponse<CustomFormHandler> {

    @Getter
    private final CustomForm form;
    private final List<CustomElement> elements;

    public CustomFormResponse(CustomFormHandler handler, List<CustomElement> elements, CustomForm form) {
        super(handler, "");
        this.elements = elements;
        this.form = form;
    }

    /**
     * Check if there is an element with id 
     */
    public boolean containsId(String elementId) {
        return elements.stream().anyMatch(element -> elementId.equals(element.getElementId()));
    }

    /**
     * Get element by index
     * @param index Element index
     * @return CustomElement
     */
    public CustomElement getElement(int index) {
        return elements.get(index);
    }

    /**
     * Get element by element id
     * @param elementId Element identifier
     * @return CustomElement
     */
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

    /**
     * Get Label by index
     * @param index Label index
     * @return Label
     */
    public Label getLabel(int index) {
        return (Label) getElement(index);
    }

    /**
     * Get Label by element id
     * @param elementId Label identifier
     * @return Label
     */
    public Label getLabel(String elementId) {
        return getElement(elementId, Label.class);
    }

    /**
     * Get all labels
     * @return List<Label>
     */
    public List<Label> getLabels() {
        return getElements(Label.class);
    }

    /**
     * Get Input by index
     * @param index Input index
     * @return Input
     */
    public Input getInput(int index) {
        return (Input) getElement(index);
    }

    /**
     * Get Input by element id
     * @param elementId Input identifier
     * @return Input
     */
    public Input getInput(String elementId) {
        return getElement(elementId, Input.class);
    }

    /**
     * Get all inputs
     * @return List<Input>
     */
    public List<Input> getInputs() {
        return getElements(Input.class);
    }

    /**
     * Get Toggle by index
     * @param index Toggle index
     * @return Toggle
     */
    public Toggle getToggle(int index) {
        return (Toggle) getElement(index);
    }

    /**
     * Get Toggle by element id
     * @param elementId Toggle identifier
     * @return Toggle
     */
    public Toggle getToggle(String elementId) {
        return getElement(elementId, Toggle.class);
    }

    /**
     * Get all toggles
     * @return List<Toggle>
     */
    public List<Toggle> getToggles() {
        return getElements(Toggle.class);
    }

    /**
     * Get Slider by index
     * @param index Slider index
     * @return Slider
     */
    public Slider getSlider(int index) {
        return (Slider) getElement(index);
    }

    /**
     * Get Slider by element id
     * @param elementId Slider identifier
     * @return Slider
     */
    public Slider getSlider(String elementId) {
        return getElement(elementId, Slider.class);
    }

    /**
     * Get all sliders
     * @return List<Slider>
     */
    public List<Slider> getSliders() {
        return getElements(Slider.class);
    }

    /**
     * Get StepSlider by index
     * @param index StepSlider index
     * @return StepSlider
     */
    public StepSlider getStepSlider(int index) {
        return (StepSlider) getElement(index);
    }

    /**
     * Get StepSlider by element id
     * @param elementId StepSlider identifier
     * @return StepSlider
     */
    public StepSlider getStepSlider(String elementId) {
        return getElement(elementId, StepSlider.class);
    }

    /**
     * Get all step sliders
     * @return List<StepSlider>
     */
    public List<StepSlider> getStepSliders() {
        return getElements(StepSlider.class);
    }

    /**
     * Get Dropdown by index
     * @param index Dropdown index
     * @return Dropdown
     */
    public Dropdown getDropdown(int index) {
        return (Dropdown) getElement(index);
    }

    /**
     * Get Dropdown by element id
     * @param elementId Dropdown identifier
     * @return Dropdown
     */
    public Dropdown getDropdown(String elementId) {
        return getElement(elementId, Dropdown.class);
    }

    /**
     * Get all dropdown
     * @return List<Dropdown>
     */
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