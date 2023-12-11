package ru.contentforge.formconstructor.form;

import ru.contentforge.formconstructor.form.handler.NoneHandler;
import lombok.Getter;

@Getter
public abstract class CloseableForm extends Form {

    protected transient NoneHandler noneHandler = null;

    public CloseableForm setNoneHandler(NoneHandler noneHandler) {
        this.noneHandler = noneHandler;
        return this;
    }
}