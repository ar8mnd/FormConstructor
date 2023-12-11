package ru.contentforge.formconstructor.form;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.form.window.FormWindow;
import ru.contentforge.formconstructor.event.PlayerFormSendEvent;
import lombok.Getter;

@Getter
public abstract class Form extends FormWindow {

    protected transient boolean async;

    public void send(Player player) {
        this.send(player, false);
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
        this.send(player, true);
    }
}