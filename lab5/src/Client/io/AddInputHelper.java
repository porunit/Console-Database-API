/**
 * A utility class for managing user input from the console.
 * Provides methods for reading and parsing user input in various Server.data types and enums.
 */
package Client.io;

import data.*;

import java.util.NoSuchElementException;
import java.util.Objects;

public class AddInputHelper {

    private static InputHandler reader = new ConsoleInputHandler();
    private static final int COORD_MIN_X = -156;
    private static final int PERSON_MIN_WEIGHT = 0;


    public static String add() {
        StringBuilder stringBuilder = new StringBuilder();
        String name = inputString(String.class, "Name(String): ", false);
        Float x = inputString(Float.class, "Coordinates\nx(Float): ", false, COORD_MIN_X);
        Long y = inputString(Long.class, "y(Long): ", false);
        Integer studentCount = inputString(Integer.class, "Students count(Integer): ", true, 0);
        FormOfEducation formOfEducation = inputEnum(FormOfEducation.class, "Form of education(DISTANCE_EDUCATION,FULL_TIME_EDUCATION, EVENING_CLASSES): ", true);
        Semester semester = inputEnum(Semester.class, "Semester(SECOND,THIRD,SIXTH): ", false);
        String nameAdmin = inputString(String.class, "Group admin\nName(String): ", false);
        Long weightAdmin = inputString(Long.class, "Weight(Long): ", false, PERSON_MIN_WEIGHT);
        Color eyeColorAdmin = AddInputHelper.inputEnum(Color.class, "Eye color(RED,YELLOW,BLACK): ", true);
        float xAdmin = inputString(Float.class, "Location\nx(float): ", false);
        Integer yAdmin = inputString(Integer.class, "y(Integer): ", false);
        int zAdmin = inputString(Integer.class, "z(int): ", false);

        stringBuilder.append(name).append("\n");
        stringBuilder.append(x).append("\n");
        stringBuilder.append(y).append("\n");
        stringBuilder.append(studentCount).append("\n");
        stringBuilder.append(formOfEducation).append("\n");
        stringBuilder.append(semester).append("\n");
        stringBuilder.append(nameAdmin).append("\n");
        stringBuilder.append(weightAdmin).append("\n");
        stringBuilder.append(eyeColorAdmin).append("\n");
        stringBuilder.append(xAdmin).append("\n");
        stringBuilder.append(yAdmin).append("\n");
        stringBuilder.append(zAdmin).append("\n");
        
        return stringBuilder.toString();
    }

    /**
     * Reads the user's input from the console as an enum of the specified type.
     * Prompts the user for input with the given message, and allows the user to enter again
     * if the input is incorrect.
     *
     * @param <T>        the enum type to read
     * @param enumName   the Class object representing the enum type to read
     * @param message    the message to prompt the user for input
     * @param isNullable whether null is an acceptable input
     * @return the user's input as an enum of the specified type
     */
    public static <T extends Enum<T>> T inputEnum(Class<T> enumName, String message, boolean isNullable) {
        while (true) {
            System.out.print(message);
            String value = reader.input();
            try {
                int id = Integer.parseInt(value) - 1;
                T[] possibleValues = enumName.getEnumConstants();
                if (id >= 0 && id < possibleValues.length) {
                    return possibleValues[id];
                }
            } catch (NumberFormatException ignore) {}
            try {
                return Enum.valueOf(enumName, Objects.requireNonNull(value.toUpperCase()));
            } catch (IllegalArgumentException e) {
                System.out.println("Incorrect input");
            } catch (NullPointerException e){
                if (isNullable) return null;
                else System.out.println("Incorrect input");
            }
        }
    }

    /**
     * Reads the user's input from the console as a Server.data type of the specified class.
     * Prompts the user for input with the given message, and allows the user to enter again
     * if the input is incorrect.
     *
     * @param dataType   the Class object representing the Server.data type to read
     * @param message    the message to prompt the user for input
     * @param isNullable whether null is an acceptable input
     * @return the user's input as a Server.data type of the specified class
     */
    public static <T> T inputString(Class<T> dataType, String message, boolean isNullable) {
        while (true) {
            System.out.print(message);
            var argument = reader.input();
            try {
                if (dataType == String.class) {
                    return (T) Objects.requireNonNull(argument);
                } else if (dataType == Double.class) {
                    return dataType.cast(Double.parseDouble(Objects.requireNonNull(argument)));
                } else if (dataType == Integer.class) {
                    return dataType.cast(Integer.parseInt(Objects.requireNonNull(argument)));
                } else if (dataType == Long.class) {
                    return dataType.cast(Long.parseLong(Objects.requireNonNull(argument)));
                } else if (dataType == Float.class) {
                    return dataType.cast(Float.parseFloat(Objects.requireNonNull(argument)));
                } else {
                    System.out.println("Unsupported number class");
                }
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input format");
            } catch (NullPointerException e) {
                if (!isNullable) System.out.println("Incorrect input format");
                else return null;
            }
        }
    }

    /**
     * A method to read user input from console and convert it to the desired Server.data type with a limit check. Allows the user to enter again
     * if the input is incorrect.
     *
     * @param <T>        the generic type of the Server.data to be inputted and returned
     * @param dataType   the Class object representing the Server.data type to be inputted and returned
     * @param message    the message to prompt the user for input
     * @param isNullable a boolean indicating whether null is a valid input or not
     * @param limit      an integer representing the minimum limit for the input, only applicable for numerical Server.data types
     * @return the parsed input of the desired Server.data type, or null if isNullable is true and input is null
     * @throws NumberFormatException if input is not a valid numerical value
     */
    public static <T> T inputString(Class<T> dataType, String message, boolean isNullable, int limit) {
        while (true) {
            System.out.print(message);
            var argument = reader.input();
            try {
                T parsedArgument = null;
                if (dataType == String.class) {
                    return (T) Objects.requireNonNull(argument);
                } else if (dataType == Double.class) {
                    parsedArgument = dataType.cast(Double.parseDouble(Objects.requireNonNull(argument)));
                    if ((double) parsedArgument < limit) throw new NumberFormatException();
                } else if (dataType == Integer.class) {
                    parsedArgument = dataType.cast(Integer.parseInt(Objects.requireNonNull(argument)));
                    if ((int) parsedArgument < limit) throw new NumberFormatException();
                } else if (dataType == Long.class) {
                    parsedArgument = dataType.cast(Long.parseLong(Objects.requireNonNull(argument)));
                    if ((long) parsedArgument < limit) throw new NumberFormatException();
                } else if (dataType == Float.class) {
                    parsedArgument = dataType.cast(Float.parseFloat(Objects.requireNonNull(argument)));
                    if ((float) parsedArgument < limit) throw new NumberFormatException();
                }
                return parsedArgument;
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input format");
            } catch (NullPointerException e) {
                if (!isNullable) System.out.println("Incorrect input format");
                else return null;
            }
        }
    }
}
