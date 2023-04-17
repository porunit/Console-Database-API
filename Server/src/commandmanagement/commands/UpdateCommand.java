package commandmanagement.commands;


import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.CollectionManager;

public class UpdateCommand extends Command {
    /**
     * Action for <b>update</b> command.
     * Receives arguments
     *
     * @param commandData command parameter
     */
    @Override
    public void execute(CommandData commandData) {
        var argument = commandData.arg();
        var outputHandler = commandData.outputHandler();
        long id;
        try {
            id = Long.parseLong(argument);
        } catch (NumberFormatException e) {
            outputHandler.print("id must be number");
            return;
        }
        if (CollectionManager.isStackEmpty()) outputHandler.print("Collection is empty");
        else if (!CollectionManager.checkId(id)) outputHandler.print("id doesn't exist");
        else {
            var group = commandData.group();
            group.setId(id);
            CollectionManager.remove(id);
            CollectionManager.add(group);
            outputHandler.print("Element updated");
        }
    }

    @Override
    public String getDescription() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}

