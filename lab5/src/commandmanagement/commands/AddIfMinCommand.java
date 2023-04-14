package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.CollectionManager;
import io.OutputHandler;

public class AddIfMinCommand extends Command {

    /**
     * Action for <b>add_if_min</b> command.
     * Receive arguments
     *
     * @param commandData command parameter
     */
    @Override
    public void execute(CommandData commandData) {
        long id;
        var argument = commandData.arg();
        OutputHandler outputHandler = commandData.outputHandler();
        try {
            id = Long.parseLong(argument);
        } catch (NumberFormatException e) {
            outputHandler.print("id must be number");
            return;
        }
        if (id <= 0) outputHandler.print("Id must be bigger then 0");
        else if (CollectionManager.checkId(id)) outputHandler.print("id exists");
        else if (id < CollectionManager.getMinId() || CollectionManager.getMinId() == 0) {
            var group = commandData.group();
            group.setId(id);
            CollectionManager.add(group);
            outputHandler.print("Element added");
        } else outputHandler.print("Id not min");
    }

    @Override
    public String getDescription() {
        return "add_if_min {element} : добавить новый элемент в коллекцию, " +
                "если его значение меньше, чем у наименьшего элемента этой коллекции";
    }
}
