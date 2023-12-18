package ru.contentforge.formconstructor.form.response;

import cn.nukkit.Player;
import ru.contentforge.formconstructor.form.handler.ModalFormHandler;

public class ModalFormResponse extends FormResponse<ModalFormHandler> {

    public ModalFormResponse(ModalFormHandler handler, String data) {
        super(handler, data);
    }

    @Override
    public void handle(Player player) {
        if (this.getHandler() != null) {
            this.getHandler().handle(player, 
            this.getData().equals("true"));
        }
    }
}
