package commandmanagement.commands;

import commandmanagement.Command;
import executionmanager.CollectionManager;
import Client.io.OutputHandler;

public class AddIfMinCommand extends Command {

    /**
     * Action for <b>add_if_min</b> command.
     * Receive arguments
     *
     * @param argument command parameter
     */
    @Override
    public void execute(String argument, OutputHandler outputHandler) {
        long id;
        try {
            id = Long.parseLong(argument);
        } catch (NumberFormatException e) {
            outputHandler.print("id must be number");
            return;
        }
        if (id <= 0) outputHandler.print("Id must be bigger then 0");
        else if (CollectionManager.checkId(id)) outputHandler.print("id exists");
        else if (id < CollectionManager.getMinId() || CollectionManager.getMinId() == 0) {
            var group = AddCommand.add();
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
