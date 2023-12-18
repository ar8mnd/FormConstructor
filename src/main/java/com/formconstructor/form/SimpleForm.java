package com.formconstructor.form;

import com.formconstructor.form.element.simple.Button;
import com.formconstructor.form.element.simple.ImageType;
import com.formconstructor.form.handler.NoneHandler;
import com.formconstructor.form.handler.SimpleFormHandler;
import com.formconstructor.form.response.SimpleFormResponse;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Getter
public class SimpleForm extends CloseableForm {

    private String title;
    private String content;
    private List<Button> buttons = new ArrayList<>();
    
    private transient SimpleFormResponse response;

    public SimpleForm() {
        this("");
    }

    public SimpleForm(String title) {
        this(title, "");
    }

    public SimpleForm(String title, String content) {
        this(title, content, null);
    }

    public SimpleForm(String title, String content, NoneHandler noneHandler) {
        this(title, content, noneHandler, null);
    }

    public SimpleForm(String title, String content, NoneHandler noneHandler, Collection<Button> buttons) {
        super(FormType.SIMPLE);
        this.title = title;
        this.content = content;
        this.setNoneHandler(noneHandler);

        if (buttons != null) {
            this.buttons.addAll(buttons);
        }
    }

    public SimpleForm setTitle(String title) {
        this.title = title;
        return this;
    }

    public SimpleForm setContent(String content) {
        this.content = content;
        return this;
    }

    public SimpleForm addContent(String addition){
        this.content += addition;
        return this;
    }

    public SimpleForm addButton(String name) {
        return addButton(name, null);
    }

    public SimpleForm addButton(String name, SimpleFormHandler handler) {
        return addButton(name, ImageType.PATH, "", handler);
    }

    public SimpleForm addButton(String name, ImageType imageType, String path) {
        return addButton(name, imageType, path, null);
    }

    public SimpleForm addButton(String name, ImageType imageType, String path, SimpleFormHandler handler) {
        return addButton(new Button(name, imageType, path, handler));
    }

    public SimpleForm addButton(Button button) {
        this.buttons.add(button);
        return this;
    }

    public SimpleForm addButtons(Button... buttons) {
        Arrays.asList(buttons).forEach(this::addButton);
        return this;
    }

    public SimpleForm addButtons(Collection<Button> buttons) {
        buttons.forEach(this::addButton);
        return this;
    }

    @Override
    public void setResponse(String data) {
        if (data.equals("null")) {
            return;
        }
    
        int buttonId;
        try {
            buttonId = Integer.parseInt(data);
        } catch (NumberFormatException ignored) {
            return;
        }
    
        if (buttonId < 0 || buttonId >= buttons.size()) {
            this.response = new SimpleFormResponse(new Button("Invalid", (p, b) -> send(p)));
            return;
        }
    
        for (int index = 0; index < buttons.size(); index++) {
            buttons.get(index).setIndex(index);
        }

        this.response = new SimpleFormResponse(buttons.get(buttonId));
    }
    
}