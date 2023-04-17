package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.CollectionManager;

@NoArguments
public class ShowCommand extends Command {

    /**
     * Action for <b>show</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) {
        var outputHandler = commandData.outputHandler();
        if (CollectionManager.isStackEmpty()) {
            outputHandler.print("Collection is empty");
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (var group : CollectionManager.getAll()) {
            builder.append(group.toString()).append("\n");
        }
        outputHandler.print(builder.toString());
    }

    @Override
    public String getDescription() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
