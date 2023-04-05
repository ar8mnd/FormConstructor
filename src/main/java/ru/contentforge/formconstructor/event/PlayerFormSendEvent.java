package ru.contentforge.formconstructor.event;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerEvent;
import ru.contentforge.formconstructor.form.Form;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.HandlerList;
import lombok.RequiredArgsConstructor;
import lombok.Getter;

@Getter
@RequiredArgsConstructor
public class PlayerFormSendEvent extends PlayerEvent implements Cancellable {
 
    private final Player player;
    private final Form form;
    private final boolean isAsync;

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }
}
