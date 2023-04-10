package commandmanagement.commands;

import commandmanagement.Command;
import executionmanager.CollectionManager;
import Client.io.OutputHandler;

@NoArguments
public class ClearCommand extends Command {

    /**
     * Action for <b>clear</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(String argument, OutputHandler outputHandler) {
        CollectionManager.clear();
    }

    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }
}
