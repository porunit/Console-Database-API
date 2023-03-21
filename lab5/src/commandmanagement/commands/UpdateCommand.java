package commandmanagement.commands;

import commandmanagement.Command;
import executionmanager.CollectionManager;
import io.OutputHandler;

public class UpdateCommand extends Command {
    /**
     * Action for <b>update</b> command.
     * Receives arguments
     *
     * @param argument command parameter
     */
    @Override
    public void execute(String argument, OutputHandler outputHandler) {
        long id = 0;
        try {
            id = Long.parseLong(argument);
        } catch (NumberFormatException e) {
            outputHandler.println("id must be number");
            return;
        }
        if (CollectionManager.isStackEmpty()) outputHandler.println("Collection is empty");
        else if (!CollectionManager.checkId(id)) outputHandler.println("id doesn't exist");
        else {
            var group = AddCommand.add();
            group.setId(id);
            CollectionManager.remove(id);
            CollectionManager.add(group);
        }
    }

    @Override
    public String getDescription() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}

