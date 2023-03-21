package commandmanagement.commands;

import commandmanagement.Command;
import executionmanager.CollectionManager;
import io.OutputHandler;

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
            outputHandler.println("id must be number");
        }
        if (CollectionManager.isStackEmpty()) outputHandler.println("Collection is empty");
        else if (!CollectionManager.checkId(id)) outputHandler.println("id doesn't exists");
        else CollectionManager.remove(id);
    }

    @Override
    public String getDescription() {
        return "remove id {element}: удалить значение элемента коллекции, id которого равен заданному";
    }
}
