package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import data.Semester;
import executionmanager.CollectionManager;
import io.OutputHandler;

import java.util.NoSuchElementException;

public class FilterBySemesterEnumCommand extends Command {


    @Override
    public void execute(CommandData commandData) {
        var argument = commandData.arg();
        OutputHandler outputHandler = commandData.outputHandler();
        Semester semester;
        try {
            semester = Semester.valueOf(argument.toUpperCase());
        } catch (NoSuchElementException | IllegalArgumentException | NullPointerException e) {
            outputHandler.print("No such semester");
            return;
        }
        var groups = CollectionManager.getFilteredBySemesterEnum(semester);
        if (groups.isEmpty()) {
            outputHandler.print("No groups with such semester");
            return;
        }
        StringBuilder builder = new StringBuilder();
        groups.forEach(x -> builder.append(x.toString()).append("\n"));
        outputHandler.print(builder.toString());
    }

    @Override
    public String getDescription() {
        return "filter_by_semester semesterEnum : вывести элементы, значение поля semesterEnum которых равно заданному";
    }
}
