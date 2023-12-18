package ru.contentforge.formconstructor.form.element;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SelectableElement {

    private final String text;
    private final Object value;
    private int index = -1;

    public SelectableElement(String text) {
        this(text, null);
    }

    public SelectableElement(String text, Object value) {
        this.text = text;
        this.value = value;
    }

    public <T> T getValue(Class<T> clazz) {
        return clazz.cast(value);
    }
}