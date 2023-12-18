package com.formconstructor;

import cn.nukkit.plugin.PluginBase;
import com.formconstructor.listener.FormListener;

public class FormConstructor extends PluginBase {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new FormListener(this), this);
    }
}