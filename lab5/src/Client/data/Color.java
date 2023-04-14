package Client.data;

import java.io.Serializable;

public enum Color implements Serializable {
    RED("Красный"),
    BLACK("Черный"),
    YELLOW("Желтый");

    private final String stringColor;

    Color(String color) {
        stringColor = color;
    }

    public String getStringColor() {
        return stringColor;
    }
}
