package ru.contentforge.formconstructor.form.element.custom;

import ru.contentforge.formconstructor.form.element.ElementType;
import ru.contentforge.formconstructor.form.element.SelectableElement;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Dropdown extends CustomElement {

    @Getter
    @SerializedName("default")
    private int defaultIndex;

    private List<String> options = new ArrayList<>();

    @Getter
    private transient List<SelectableElement> elements = new ArrayList<>();

    @Getter
    private transient int selectedIndex = -1;

    public Dropdown() {
        this("");
    }

    public Dropdown(String name) {
        this(name, new ArrayList<>());
    }

    public Dropdown(List<SelectableElement> elements) {
        this("", elements);
    }

    public Dropdown(String name, List<SelectableElement> elements) {
        this(name, elements, 0);
    }

    public Dropdown(String name, List<SelectableElement> elements, int defaultIndex) {
        super(name, ElementType.DROPDOWN);
        this.defaultIndex = defaultIndex;
        this.addElements(elements);
    }

    public Dropdown addElement(SelectableElement element) {
        element.setIndex(elements.size());
        elements.add(element);
        options.add(element.getName());
        return this;
    }

    public Dropdown addElement(String name, Object value) {
        return this.addElement(new SelectableElement(name, value));
    }

    public Dropdown addElement(String name) {
        return this.addElement(name, null);
    }

    public Dropdown addElements(Collection<SelectableElement> elements) {
        elements.forEach(this::addElement);
        return this;
    }

    public Dropdown setDefaultIndex(int defaultIndex) {
        this.defaultIndex = defaultIndex;
        return this;
    }

    public SelectableElement getDefault() {
        return elements.isEmpty() ? null : elements.get(defaultIndex);
    }

    public SelectableElement getValue() {
        return elements.isEmpty() ? null : elements.get(selectedIndex);
    }

    @Override
    public boolean respond(Object value) {
        this.selectedIndex = ((Double) value).intValue();
        return !(elements.isEmpty() || selectedIndex < 0 || selectedIndex >= elements.size());
    }
}