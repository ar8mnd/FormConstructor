package ru.contentforge.formconstructor.task;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.scheduler.AsyncTask;
import ru.contentforge.formconstructor.event.PlayerFormCloseEvent;
import ru.contentforge.formconstructor.form.CloseableForm;
import ru.contentforge.formconstructor.form.Form;
import ru.contentforge.formconstructor.form.handler.NoneHandler;
import ru.contentforge.formconstructor.form.response.CustomFormResponse;
import ru.contentforge.formconstructor.form.response.ModalFormResponse;
import ru.contentforge.formconstructor.form.response.SimpleFormResponse;

public class FormHandlingTask extends AsyncTask {

    private final FormResponse response;
    private final Form form;
    private final Player player;

    public FormHandlingTask(FormResponse response, Form form, Player player) {
        this.response = response;
        this.form = form;
        this.player = player;
    }

    @Override
    public void onRun() {
        if (response == null && form instanceof CloseableForm closeableForm) {
            NoneHandler noneHandler = closeableForm.getNoneHandler();
            
            PlayerFormCloseEvent event = new PlayerFormCloseEvent(player, form);
            Server.getInstance().getPluginManager().callEvent(event);
            
            if (noneHandler != null) noneHandler.handle(player);
            return;
        }

        if (response instanceof ModalFormResponse modalResponse) {
            modalResponse.handle(player);
            return;
        }

        if (response instanceof SimpleFormResponse simpleResponse) {
            simpleResponse.handle(player);
            return;
        }

        if (response instanceof CustomFormResponse customResponse) {
            customResponse.handle(player);
            return;
        }
    }
}