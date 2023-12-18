package ru.contentforge.formconstructor;

import ru.contentforge.formconstructor.form.SimpleForm;
import ru.contentforge.formconstructor.form.element.simple.Button;
import ru.contentforge.formconstructor.form.element.simple.ImageType;

public class Test {

    public static void main(String[] args) {
        SimpleForm form = new SimpleForm("123");

        form.addButton(new Button("Button")
                .setImage(ImageType.PATH, "textures/...")
                .onClick((pl, b) -> {
                    pl.sendMessage("Button id: " + b.getIndex());
                }));

        form.send(null);
    }
}
