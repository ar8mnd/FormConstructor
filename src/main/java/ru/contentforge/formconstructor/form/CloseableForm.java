package ru.contentforge.formconstructor.form;

import ru.contentforge.formconstructor.form.handler.NoneHandler;
import lombok.Getter;

abstract public class CloseableForm extends Form {

    @Getter protected transient NoneHandler noneHandler = null;

    public CloseableForm setNoneHandler(NoneHandler noneHandler) {
        this.noneHandler = noneHandler;
        return this;
    }

}
