package commandmanagement.commands;

import commandmanagement.Command;
import executionmanager.CollectionManager;
import Client.io.OutputHandler;

@NoArguments
public class ShowCommand extends Command {

    /**
     * Action for <b>show</b> command.
     * Doesn't receive arguments
     */
    public void execute(String argument, OutputHandler outputHandler) {
        if(CollectionManager.isStackEmpty()){
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
