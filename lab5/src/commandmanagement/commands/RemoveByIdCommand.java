package commandmanagement.commands;

import commandmanagement.Command;
import executionmanager.CollectionManager;
import Client.io.OutputHandler;

public class RemoveByIdCommand extends Command {

    /**
     * Action for <b>remove</b> command.
     * Receive arguments
     *
     * @param argument command parameter
     */
    public void execute(String argument, OutputHandler outputHandler) {
        long id = 0;
        try {
            id = Long.parseLong(argument);
        } catch (NumberFormatException e) {
            outputHandler.print("id must be number");
        }
        if (CollectionManager.isStackEmpty()) outputHandler.print("Collection is empty");
        else if (!CollectionManager.checkId(id)) outputHandler.print("id doesn't exists");
        else{
            CollectionManager.remove(id);
            outputHandler.print("Element removed");
        }
    }

    @Override
    public String getDescription() {
        return "remove id {element}: удалить значение элемента коллекции, id которого равен заданному";
    }
}
