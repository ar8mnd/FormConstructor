package ru.contentforge.formconstructor.form.response;

import cn.nukkit.Player;
import ru.contentforge.formconstructor.form.element.simple.Button;
import ru.contentforge.formconstructor.form.handler.SimpleFormHandler;

public class SimpleFormResponse extends FormResponse<SimpleFormHandler> {

    private final Button button;

    public SimpleFormResponse(Button button) {
        super(button.getHandler(), "");
        this.button = button;
    }

    @Override
    public void handle(Player player) {
        if (this.getHandler() != null) {
            this.getHandler().handle(player, button);
        }
    }
}