package data;

public enum Semester {
    SECOND("Второй"),
    THIRD("Третий"),
    SIXTH("Шестой");
    private String stringSemester;
    Semester(String color) {
        stringSemester = color;
    }
    public String getStringSemester(){
        return stringSemester;
    }
}
