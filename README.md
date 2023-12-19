![logo by @tolimag](.github/logo.png)

[![License: MIT](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Version](https://img.shields.io/badge/version-2.0.0-brightgreen)](https://github.com/ContentForge/FormConstructor/releases/tag/1.1.3)
[![CloudBurst](https://img.shields.io/badge/CloudBurst-1.1.2-brightgreen)](https://cloudburstmc.org/resources/formconstructor.738/)

> [!IMPORTANT]
> The current version of FormConstructor plugin is 2.0.0, if your plugin does not support this version you can download FormConstructor version 1.1.4 from the link below:
> [Download FormConstructor 1.1.4](https://github.com/MEFRREEX/FormConstructor/tree/1.1.4)

🤔 Introduction
------------- 

Library is designed to simplify the creation and handling of forms.
It has a few key advantages over other  form libraries:

- Forms are processed using a lambda, which is passed when the form itself is created, and not by catching events.
- For each button we can set a lambda function in SimpleForm.
- In SimpleForm we get a button object as a response, where we can get its text and index.
- In CustomForm we can mark elements with an identifier to conveniently get this element in its handler. We can get element by id and its index.
- Easy async handling.

🛠 Examples
-------------

Creating a SimpleForm:

```java
SimpleForm form = new SimpleForm("Sample title");
// Adding form content
form.addContent("New content line");
    
// Easiest way to add a button
form.addButton("Button", (pl, b) -> {
        pl.sendMessage("Button clicked: " + b.getName() + " (" + b.getIndex() + ")");
    })
    // Button with image
    .addButton("Button with image", ImageType.PATH, "textures/items/diamond")
    // Another way to add a button
    .addButton(new Button("Another button")
        .setImage(ImageType.PATH, "textures/blocks/stone")
        .onClick((pl, b) -> {
            pl.sendMessage("Another button clicked: " + b.getName() + " (" + b.getIndex() + ")");
        }));

// Setting the form close handler
form.setNoneHandler(pl -> {
    pl.sendMessage("You closed the form!");
});

form.send(player);
```

Creating a ModalForm:

```java
ModalForm form = new ModalForm("Test modal form");
form.addContent("New content line");

form.setPositiveButton("Positive button")
    .setNegativeButton("Negative button");

// Setting the form handler
// Result returns true if a positive button was ckicked and false if a negative button was ckicked
form.setHandler((pl, result) -> {
    pl.sendMessage("You clicked " + (result ? "correct" : "wrong") + " button!");
});

// Setting the form close handler
form.setNoneHandler(pl -> pl.sendMessage("You closed the form!"));
form.send(player);
```

Creating a CustomForm:

```java
CustomForm form = new CustomForm("Test custom form");

// Options list 
List<SelectableElement> elements = List.of(
    new SelectableElement("Option 1"),
    new SelectableElement("Option 2"),
    // SelectableElement may be named and may contain a value
    new SelectableElement("Option with value", 15)
);

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
    .addElement("dropdown1", new Dropdown("Second dropdown", elements))
    .addElement("toggle", new Toggle("Toggle"));

// Setting the form handler
form.setHandler((pl, response) -> {
    String input = response.getInput("input").getValue();

    SelectableElement slider = response.getStepSlider("slider").getValue();
    SelectableElement dropdown = response.getDropdown("dropdown").getValue();

    // Getting the value we set in SelectableElement
    Integer dropdownValue = response.getDropdown("dropdown1").getValue().getValue(Integer.class);

    boolean toggle = response.getToggle("toggle").getValue();

    pl.sendMessage("Input: " + input + ", Slider: " + slider + ", Dropdown: " + dropdown + ", Toggle: " + toggle);
    pl.sendMessage("Second dropdown value: " + dropdownValue);
});

form.send(player);
```

### Async handling
Also you can use method `sendAsync(player)` or `send(player, true)` for using async form handling.
But this may cause some restrictions. What exactly - I don't know.

💰 Donate
-------------

- [DonationAlerts](https://www.donationalerts.com/r/qpexlegendary)
