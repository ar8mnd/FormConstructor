package com.formconstructor.form;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.form.window.FormWindow;
import com.formconstructor.event.PlayerFormSendEvent;
import lombok.Getter;

@Getter
public abstract class Form extends FormWindow {

    private final FormType type;
    private transient boolean async;

    public Form(FormType type) {
        this.type = type;
    }

    public void send(Player player) {
        send(player, false);
    }

    public void send(Player player, boolean async) {
        PlayerFormSendEvent event = new PlayerFormSendEvent(player, this, async);
        Server.getInstance().getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            this.async = event.isAsync();
            player.showFormWindow(this);
        }
    }

    public void sendAsync(Player player) {
        send(player, true);
    }
}