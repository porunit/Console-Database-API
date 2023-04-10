package commandmanagement.commands;

import commandmanagement.Command;
import data.*;
import executionmanager.CollectionManager;
import io.AddInputHelper;
import Client.io.OutputHandler;
import ioserver.ServerInputHandler;

import java.util.Scanner;

@NoArguments
public class AddCommand extends Command {

    /**
     * Creates a new StudyGroup instance based on user input and adds it to the collection
     *
     * @return the newly created StudyGroup instance
     */
    public static StudyGroup add() {
        var object = ServerInputHandler.input().split("\n");
        String name = object[0];
        Float x = Float.parseFloat(object[1]);
        Long y = Long.parseLong(object[2]);
        Coordinates coordinates = new Coordinates(x, y);
        Integer studentCount = Integer.parseInt(object[3]);
        FormOfEducation formOfEducation = FormOfEducation.valueOf(object[4]);
        Semester semester = Semester.valueOf(object[5]);
        String nameAdmin = object[6];
        Long weightAdmin = Long.parseLong(object[7]);
        Color eyeColorAdmin = Color.valueOf(object[8]);
        float xAdmin = Float.parseFloat(object[9]);
        Integer yAdmin = Integer.parseInt(object[10]);
        int zAdmin = Integer.parseInt(object[11]);

        Location location = new Location(xAdmin, yAdmin, zAdmin);
        Person groupAdmin = new Person(nameAdmin, weightAdmin, eyeColorAdmin, location);
        return new StudyGroup(createID(), name, coordinates, studentCount, formOfEducation, semester, groupAdmin);
    }

    private static long createID() {
        long id = CollectionManager.getAmountElements() + 1;
        while (CollectionManager.checkId(id)) {
            id = id + 1;
        }
        CollectionManager.appendId(id);
        return id;
    }

    /**
     * Action for <b>add</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(String argument, OutputHandler outputHandler) {
        CollectionManager.add(add());
        outputHandler.print("Element added");
    }

    @Override
    public String getDescription() {
        return "add : добавить новый элемент в коллекцию";
    }
}
