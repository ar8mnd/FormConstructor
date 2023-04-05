package ru.contentforge.formconstructor;

import cn.nukkit.plugin.PluginBase;
import ru.contentforge.formconstructor.listener.FormListener;

public class FormConstructor extends PluginBase {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new FormListener(this), this);
    }
}
