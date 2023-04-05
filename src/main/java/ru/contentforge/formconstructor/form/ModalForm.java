package ru.contentforge.formconstructor.form;

import cn.nukkit.Player;
import ru.contentforge.formconstructor.form.handler.ModalFormHandler;
import ru.contentforge.formconstructor.form.response.ModalFormResponse;
import com.google.gson.annotations.SerializedName;
import lombok.experimental.Accessors;
import lombok.Getter;
import lombok.Setter;

@Accessors(chain = true)
public class ModalForm extends CloseableForm {

    @SerializedName("type") private final String type = "modal";
    @Getter @Setter @SerializedName("title") protected String title;
    @Getter @Setter @SerializedName("content") protected String content;
    @Getter @Setter @SerializedName("button1") protected String positiveButton;
    @Getter @Setter @SerializedName("button2") protected String negativeButton;
    @Setter protected transient ModalFormHandler handler;
    @Getter protected transient ModalFormResponse response = null;

    public ModalForm() {
        this("");
    }

    public ModalForm(String title) {
        this(title, "");
    }

    public ModalForm(String title, String content) {
        this(title, content, "");
    }

    public ModalForm(String title, String content, String positiveButton) {
        this(title, content, positiveButton, "");
    }

    public ModalForm(String title, String content, String positiveButton, String negativeButton) {
        this(title, content, positiveButton, negativeButton, null);
    }

    public ModalForm(String title, String content, String positiveButton, String negativeButton, ModalFormHandler handler) {
        this.title = title;
        this.content = content;
        this.positiveButton = positiveButton;
        this.negativeButton = negativeButton;
        this.handler = handler;
    }

    public ModalForm addContent(String addition) {
        this.content += addition;
        return this;
    }

    @Override
    public void setResponse(String data) {
        if (data.equals("null") || handler == null) return;

        this.response = new ModalFormResponse(handler, data);
    }

    public void send(Player player, ModalFormHandler handler) {
        this.setHandler(handler);
        this.send(player);
    }
}
