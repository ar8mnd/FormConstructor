package ru.contentforge.formconstructor.form;

import ru.contentforge.formconstructor.form.element.Button;
import ru.contentforge.formconstructor.form.element.ImageType;
import ru.contentforge.formconstructor.form.handler.NoneHandler;
import ru.contentforge.formconstructor.form.handler.SimpleFormHandler;
import ru.contentforge.formconstructor.form.response.SimpleFormResponse;
import com.google.gson.annotations.SerializedName;
import lombok.experimental.Accessors;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

@Accessors(chain = true)
public class SimpleForm extends CloseableForm {

    @SerializedName("type") protected final String type = "form";
    @Getter @Setter @SerializedName("title") protected String title;
    @Getter @Setter @SerializedName("content") protected String content;
    @Getter @Setter @SerializedName("buttons") protected ArrayList<Button> buttons = new ArrayList<>();
    @Getter protected transient SimpleFormResponse response = null;

    public SimpleForm() {
        this("");
    }

    public SimpleForm(String title) {
        this(title, "");
    }

    public SimpleForm(String title, String content) {
        this(title, content,null);
    }

    public SimpleForm(String title, String content, NoneHandler noneHandler) {
        this(title, content, noneHandler, null);
    }

    public SimpleForm(String title, String content, NoneHandler noneHandler, Collection<Button> buttons) {
        this.title = title;
        this.content = content;
        this.noneHandler = noneHandler;

        if(buttons == null) return;
        for(Button button: buttons) addButton(button);
    }

    public SimpleForm addContent(String addition) {
        this.content += addition;
        return this;
    }

    public SimpleForm addButton(SimpleFormHandler handler) {
        return addButton("", handler);
    }

    public SimpleForm addButton(String text, SimpleFormHandler handler) {
        return addButton(text, ImageType.PATH, "", handler);
    }

    public SimpleForm addButton(String text, ImageType imageType, String path, SimpleFormHandler handler) {
        return addButton(new Button(text, imageType, path, handler));
    }

    public SimpleForm addButton(Button button) {
        buttons.add(button);
        return this;
    }

    public SimpleForm addButtons(Button... buttons) {
        for(Button button: buttons) addButton(button);
        return this;
    }

    public SimpleForm addButtons(Collection<Button> buttons) {
        for(Button button: buttons) addButton(button);
        return this;
    }

    @Override
    public void setResponse(String data) {
        if(data.equals("null")) return;

        int buttonId;
        try {
            buttonId = Integer.parseInt(data);
        } catch (Exception var4) {
            return;
        }
        if(buttonId >= buttons.size() || buttonId < 0){
            this.response = new SimpleFormResponse(new Button("Invalid", (p, b) -> send(p)));
            return;
        }

        for(int i = 0; i < buttons.size(); i++) buttons.get(i).index = i;

        this.response = new SimpleFormResponse(buttons.get(buttonId));
    }

}
