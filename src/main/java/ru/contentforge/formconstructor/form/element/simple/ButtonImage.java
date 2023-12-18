package ru.contentforge.formconstructor.form.element.simple;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class ButtonImage {

    @SerializedName("type") private final ImageType type;
    @SerializedName("data") private final String path;

    public ButtonImage() {
        this(ImageType.PATH, "");
    }

    public ButtonImage(ImageType type, String path) {
        this.path = path;
        this.type = type;
    }
}