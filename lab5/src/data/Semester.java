package data;

import java.io.Serializable;

public enum Semester implements Serializable {
    SECOND("Второй"),
    THIRD("Третий"),
    SIXTH("Шестой");
    private final String stringSemester;

    Semester(String color) {
        stringSemester = color;
    }

    public String getStringSemester() {
        return stringSemester;
    }
}
