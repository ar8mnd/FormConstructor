package com.formconstructor.event;

import cn.nukkit.Player;
import cn.nukkit.event.HandlerList;
import com.formconstructor.form.Form;
import lombok.Getter;

@Getter
public class PlayerFormCloseEvent extends FormEvent {
 
    private final Player player;

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    public PlayerFormCloseEvent(Player player, Form form) {
        super(form);
        this.player = player;
    }
}