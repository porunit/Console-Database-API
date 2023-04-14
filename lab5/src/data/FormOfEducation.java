package data;

import java.io.Serializable;

public enum FormOfEducation implements Serializable {
    DISTANCE_EDUCATION("Дистанционное обучение"),
    FULL_TIME_EDUCATION("Очное обучение"),
    EVENING_CLASSES("Вечерние классы");

    private final String stringFormOfEducation;

    FormOfEducation(String color) {
        stringFormOfEducation = color;
    }

    public String getStringFormOfEducation() {
        return stringFormOfEducation;
    }
}
