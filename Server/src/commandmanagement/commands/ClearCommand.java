package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.CollectionManager;
import org.apache.log4j.Logger;

@NoArguments
public class ClearCommand extends Command {
    private final org.apache.log4j.Logger log = Logger.getLogger(ClearCommand.class);

    /**
     * Action for <b>clear</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) {
        CollectionManager.clear();
        commandData.outputHandler().print("Collection cleared");
        log.info("Collection cleared");
    }

    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }
}
