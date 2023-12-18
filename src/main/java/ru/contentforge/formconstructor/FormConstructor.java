package ru.contentforge.formconstructor;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;
import ru.contentforge.formconstructor.form.CustomForm;
import ru.contentforge.formconstructor.form.ModalForm;
import ru.contentforge.formconstructor.form.SimpleForm;
import ru.contentforge.formconstructor.form.element.SelectableElement;
import ru.contentforge.formconstructor.form.element.custom.Dropdown;
import ru.contentforge.formconstructor.form.element.custom.Input;
import ru.contentforge.formconstructor.form.element.custom.StepSlider;
import ru.contentforge.formconstructor.form.element.custom.Toggle;
import ru.contentforge.formconstructor.form.element.simple.Button;
import ru.contentforge.formconstructor.form.element.simple.ImageType;
import ru.contentforge.formconstructor.listener.FormListener;

public class FormConstructor extends PluginBase {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new FormListener(this), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            switch(command.getName()) {
                case "simpleform" -> {
                    simpleForm(player);
                }
                case "customform" -> {
                    customForm(player);
                }
                case "modalform" -> {
                    modalForm(player);   
                }
            }
        }
        return true;
    }

    private void simpleForm(Player player) {
        SimpleForm form = new SimpleForm("Test simple form");
        form.addContent("New content line");

        form.addButton("Button 1", (pl, b) -> {
                pl.sendMessage("Button 1 clicked: " + b.getName() + " (" + b.getIndex() + ")");
            })
            .addButton(new Button("Button 2")
                .setImage(ImageType.PATH, "textures/blocks/stone")
                .onClick((pl, b) -> {
                    pl.sendMessage("Button 2 clicked: " + b.getName() + " (" + b.getIndex() + ")");
                }));

        form.send(player);
    }

    private void customForm(Player player) {
        CustomForm form = new CustomForm("Test custom form");

        form.addElement("Test label")
            .addElement("input", new Input("Input")
                .setPlaceholder("Text")
                .setDefaultValue("Default value"))
            .addElement("slider", new StepSlider("Step slider")
                .addElement("1")
                .addElement("2")
                .addElement("3"))
            .addElement("dropdown", new Dropdown("Dropdown")
                .addElement("Element 1")
                .addElement("Element 2")
                .addElement("Element 3"))
            .addElement("toggle", new Toggle("Toggle"));

        form.setHandler((pl, response) -> {
            String input = response.getInput("input").getValue();

            SelectableElement slider = response.getStepSlider("slider").getValue();
            SelectableElement dropdown = response.getDropdown("dropdown").getValue();

            boolean toggle = response.getToggle("toggle").getValue();

            pl.sendMessage("Input: " + input + ", Slider: " + slider + ", Dropdown: " + dropdown + ", Toggle: " + toggle);
        });

        form.send(player);
    }

    private void modalForm(Player player) {
        ModalForm form = new ModalForm("Test modal form");
        form.addContent("New content line");

        form.setPositiveButton("Positive button")
            .setNegativeButton("Negative button");
        
        form.setHandler((pl, result) -> {
            pl.sendMessage("You clicked " + (result ? "correct" : "wrong") + " button!");
        });

        form.setNoneHandler(pl -> pl.sendMessage("You closed the form"));
        form.send(player);
    }
}
