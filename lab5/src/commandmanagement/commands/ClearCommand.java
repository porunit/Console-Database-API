package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.CollectionManager;

@NoArguments
public class ClearCommand extends Command {

    /**
     * Action for <b>clear</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) {
        CollectionManager.clear();
        commandData.outputHandler().print("Collection cleared");
    }

    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }
}
