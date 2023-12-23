package com.formconstructor.form.element.custom;

import com.formconstructor.form.element.ElementType;
import com.formconstructor.form.element.SelectableElement;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StepSlider extends CustomElement {

    @Getter
    @SerializedName("default")
    private int defaultIndex;

    @SerializedName("steps")
    private List<String> options = new ArrayList<>();

    @Getter
    private transient List<SelectableElement> elements = new ArrayList<>();

    @Getter
    private transient int selectedIndex = -1;

    public StepSlider() {
        this("");
    }

    public StepSlider(String name) {
        this(name, new ArrayList<>());
    }

    public StepSlider(List<SelectableElement> elements) {
        this("", elements);
    }

    public StepSlider(String name, List<SelectableElement> elements) {
        this(name, elements, 0);
    }

    public StepSlider(String name, List<SelectableElement> elements, int defaultIndex) {
        super(name, ElementType.STEP_SLIDER);
        this.defaultIndex = defaultIndex;
        this.addSteps(elements);
    }

    @Override
    public StepSlider setName(String name) {
        return (StepSlider) super.setName(name);
    }

    public StepSlider addStep(SelectableElement element) {
        element.setIndex(elements.size());
        elements.add(element);
        options.add(element.getName());
        return this;
    }

    public StepSlider addStep(String name, Object value) {
        addStep(new SelectableElement(name, value));
        return this;
    }

    public StepSlider addStep(String name) {
        addStep(name, null);
        return this;
    }

    public StepSlider addSteps(Collection<SelectableElement> elements) {
        elements.forEach(this::addStep);
        return this;
    }

    public StepSlider setDefaultIndex(int defaultIndex) {
        this.defaultIndex = defaultIndex;
        return this;
    }

    public SelectableElement getDefault() {
        return (elements.size() == 0 || (elements.size() == 1 && defaultIndex == 1)) ? null : elements.get(defaultIndex);
    }

    public SelectableElement getValue() {
        return (elements.size() == 0 || (elements.size() == 1 && selectedIndex == 1)) ? null : elements.get(selectedIndex);
    }

    @Override
    public boolean respond(Object data) {
        this.selectedIndex = ((Double) data).intValue();

        if (elements.isEmpty() || selectedIndex < 0 || (elements.size() == 1 && selectedIndex == 1)) {
            return true;
        }

        return selectedIndex < elements.size();
    }
}
