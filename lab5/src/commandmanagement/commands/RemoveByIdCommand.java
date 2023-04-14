package commandmanagement.commands;


import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.CollectionManager;

public class RemoveByIdCommand extends Command {

    /**
     * Action for <b>remove</b> command.
     * Receive arguments
     *
     * @param commandData command parameter
     */
    @Override
    public void execute(CommandData commandData) {
        var argument = commandData.arg();
        var outputHandler = commandData.outputHandler();
        long id = 0;
        try {
            id = Long.parseLong(argument);
        } catch (NumberFormatException e) {
            outputHandler.print("id must be number");
        }
        if (CollectionManager.isStackEmpty()) outputHandler.print("Collection is empty");
        else if (!CollectionManager.checkId(id)) outputHandler.print("id doesn't exists");
        else {
            CollectionManager.remove(id);
            outputHandler.print("Element removed");
        }
    }

    @Override
    public String getDescription() {
        return "remove id {element}: удалить значение элемента коллекции, id которого равен заданному";
    }
}
