package commandmanagement.commands;

import commandmanagement.Command;
import data.Semester;
import executionmanager.CollectionManager;
import Client.io.OutputHandler;

import java.util.NoSuchElementException;

public class FilterBySemesterEnumCommand extends Command {
    /**
     * Action for <b>filter_by_semester</b> command.
     * Receive arguments
     *
     * @param argument command parameter
     */
    public void execute(String argument, OutputHandler outputHandler) {
        Semester semester = null;
        try {
            semester = Semester.valueOf(argument);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            outputHandler.print("No such semester");
        }
        var groups = CollectionManager.getFilteredBySemesterEnum(semester);
        if (!groups.isEmpty()) {
            for (var it : groups) {
                outputHandler.print(it.toString());
            }
        }
    }

    @Override
    public String getDescription() {
        return "filter_by_semester_enum semesterEnum : вывести элементы, значение поля semesterEnum которых равно заданному";
    }
}
