package commandmanagement.commands;


import commandmanagement.Command;
import commandmanagement.CommandData;
import data.StudyGroup;
import executionmanager.CollectionManager;

import java.util.Comparator;

@NoArguments
public class PrintDescendingCommand extends Command {

    /**
     * Action for <b>print_descending</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) {
        var groups = CollectionManager.getAll();
        StringBuilder builder = new StringBuilder();
        groups.stream()
                .sorted(Comparator.comparingLong(StudyGroup::getId).reversed())
                .forEach(x-> builder.append(x).append("\n"));
        commandData.outputHandler().print(builder.toString());
    }

    @Override
    public String getDescription() {
        return "print_descending : вывести элементы коллекции в порядке убывания";
    }
}
