package commandmanagement.commands;


import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.CollectionManager;

@NoArguments
public class PrintDescendingCommand extends Command {

    /**
     * Action for <b>print_descending</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) {
        var groups = CollectionManager.getAll();
        StringBuilder builder = new StringBuilder();
        for (var i = groups.size() - 1; i > 0; i--) {
            builder.append(groups.get(i).toString()).append("\n");
        }
        commandData.outputHandler().print(builder.toString());
    }

    @Override
    public String getDescription() {
        return "print_descending : вывести элементы коллекции в порядке убывания";
    }
}
