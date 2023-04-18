package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;

import data.*;
import io.AddInputHelper;
import io.network.C2SPackage;

import java.io.IOException;


@NoArguments
public class AddCommand extends Command {

    /**
     * Creates a new StudyGroup instance based on user input and adds it to the collection
     *
     * @return the newly created StudyGroup instance
     */
    public static StudyGroup add() {
        String name = AddInputHelper.inputString(String.class, "Name(String):", false);
        Float x = AddInputHelper.inputString(Float.class, "Coordinates\nx(Float): ", false, Coordinates.getMinX());
        Long y = AddInputHelper.inputString(Long.class, "y(Long): ", false);
        Coordinates coordinates = new Coordinates(x, y);
        Integer studentCount = AddInputHelper.inputString(Integer.class, "Students count(Integer): ", true, 0);
        FormOfEducation formOfEducation = AddInputHelper.inputEnum(FormOfEducation.class, "Form of education(DISTANCE_EDUCATION,FULL_TIME_EDUCATION, EVENING_CLASSES): ", true);
        Semester semester = AddInputHelper.inputEnum(Semester.class, "Semester(SECOND,THIRD,SIXTH): ", false);
        String nameAdmin = AddInputHelper.inputString(String.class, "Group admin\nName(String): ", false);
        Long weightAdmin = AddInputHelper.inputString(Long.class, "Weight(Long): ", false, Person.getMinWeight());
        Color eyeColorAdmin = AddInputHelper.inputEnum(Color.class, "Eye color(RED,YELLOW,BLACK): ", true);
        float xAdmin = AddInputHelper.inputString(Float.class, "Location\nx(float): ", false);
        Integer yAdmin = AddInputHelper.inputString(Integer.class, "y(Integer): ", false);
        int zAdmin = AddInputHelper.inputString(Integer.class, "z(int): ", false);

        Location location = new Location(xAdmin, yAdmin, zAdmin);
        Person groupAdmin = new Person(nameAdmin, weightAdmin, eyeColorAdmin, location);
        return new StudyGroup(2, name, coordinates, studentCount, formOfEducation, semester, groupAdmin);
    }


    /**
     * Action for <b>add</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) {
        C2SPackage sPackage = new C2SPackage("add", null, add());
        try {
            commandData.outputHandler().printObj(sPackage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getDescription() {
        return "add : добавить новый элемент в коллекцию";
    }
}
