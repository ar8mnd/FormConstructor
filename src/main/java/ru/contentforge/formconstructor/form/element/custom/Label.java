package ru.contentforge.formconstructor.form.element.custom;

import ru.contentforge.formconstructor.form.element.ElementType;

public class Label extends CustomElement {

    public Label() {
        this("");
    }

    public Label(String text) {
        super(text, ElementType.LABEL);
    }

    @Override
    public boolean respond(Object value) {
        return true;
    }
}