package ru.contentforge.formconstructor.form;

import cn.nukkit.Player;
import ru.contentforge.formconstructor.form.element.CustomFormElement;
import ru.contentforge.formconstructor.form.element.Label;
import ru.contentforge.formconstructor.form.element.validator.ValidationField;
import ru.contentforge.formconstructor.form.handler.CustomFormHandler;
import ru.contentforge.formconstructor.form.response.CustomFormResponse;
import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashSet;

@Accessors(chain = true)
public class CustomForm extends CloseableForm {

    @SerializedName("type")
    protected final String type = "custom_form";

    @Getter @Setter 
    @SerializedName("title")
    protected String title;

    @Getter
    @SerializedName("content")
    protected ArrayList<CustomFormElement> elements = new ArrayList<>();

    @Setter
    protected transient CustomFormHandler handler;

    @Getter
    protected transient CustomFormResponse response = null;

    protected final transient HashSet<String> containsId = new HashSet<>();

    @Getter
    protected transient boolean validated = true;

    public CustomForm() {
        this("", null);
    }

    public CustomForm(String title) {
        this(title, null);
    }

    public CustomForm(CustomFormHandler handler) {
        this("", handler);
    }

    public CustomForm(String title, CustomFormHandler handler) {
        this.title = title;
        this.handler = handler;
    }

    public CustomForm addElement(String text) {
        return addElement(new Label(text));
    }

    public CustomForm addElement(CustomFormElement element) {
        elements.add(element);
        return this;
    }

    public CustomForm addElement(String elementId, CustomFormElement element) {
        element.elementId = elementId;
        containsId.add(elementId);
        return addElement(element);
    }

    @Override
    public void setResponse(String data) {
        if (data.equals("null")) return;

        Object[] result = new Gson().fromJson(data, Object[].class);
        for (int i = 0; i < elements.size(); i++) {
            CustomFormElement element = elements.get(i);
            if (!element.respond(result[i])) {
                this.response = new CustomFormResponse((player, response) -> send(player), elements, containsId, this);
                return;
            }

            if (element instanceof ValidationField) {
                if (this.validated && !((ValidationField) element).getValidatorResult()) this.validated = false;
            }
        }

        for (int i = 0; i < elements.size(); i++) elements.get(i).index = i;

        this.response = new CustomFormResponse(handler, elements, containsId, this);
    }

    public void send(Player player, CustomFormHandler handler) {
        this.setHandler(handler);
        this.send(player);
    }
}
