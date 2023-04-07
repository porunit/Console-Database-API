package commandmanagement.commands;

import commandmanagement.Command;
import executionmanager.CollectionManager;
import io.OutputHandler;

@NoArguments
public class PrintDescendingCommand extends Command {

    /**
     * Action for <b>print_descending</b> command.
     * Doesn't receive arguments
     */
    public void execute(String argument, OutputHandler outputHandler) {
        var groups = CollectionManager.getAll();
        for (var i = groups.size() - 1; i > 0; i--) {
            outputHandler.println(groups.get(i).toString());
        }
    }

    @Override
    public String getDescription() {
        return "print_descending : вывести элементы коллекции в порядке убывания";
    }
}
