package Client.data;

import data.Color;
import data.Location;
import exceptions.WrongDataTypeException;

import java.io.Serializable;

public class Person implements Serializable {
    private static final int MIN_WEIGHT = 0;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long weight; //Поле не может быть null, Значение поля должно быть больше 0
    private data.Color eyeColor; //Поле может быть null
    private data.Location location; //Поле не может быть null

    public Person() {
    }

    public Person(String name, Long weight, data.Color eyeColor, data.Location location) {
        setName(name);
        setWeight(weight);
        setEyeColor(eyeColor);
        setLocation(location);
    }

    public static int getMinWeight() {
        return MIN_WEIGHT;
    }

    public data.Color getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public data.Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        if (location != null) this.location = location;
        else throw new WrongDataTypeException();
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        if (weight <= MIN_WEIGHT) throw new WrongDataTypeException();
        else this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.equals("")) this.name = name;
        else throw new WrongDataTypeException();
    }
}
