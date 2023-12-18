package com.formconstructor.form.element;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class FormElement {

    @SerializedName("text")
    private String name;
    
    private transient int index = -1;

    public FormElement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}