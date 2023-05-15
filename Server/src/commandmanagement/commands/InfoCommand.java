package commandmanagement.commands;


import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.CollectionManager;

@NoArguments
public class InfoCommand extends Command {

    /**
     * Action for <b>info</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) {
        commandData.outputHandler().print("type: " + CollectionManager.getCollectionType() + "\n" +
                "creation date: " + CollectionManager.getCreationDate() + "\n" +
                "size: " + CollectionManager.getAmountElements() +
                "\n");
    }

    @Override
    public String getDescription() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
