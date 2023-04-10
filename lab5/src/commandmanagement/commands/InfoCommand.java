package commandmanagement.commands;

import commandmanagement.Command;
import executionmanager.CollectionManager;
import Client.io.OutputHandler;

@NoArguments
public class InfoCommand extends Command {

    /**
     * Action for <b>info</b> command.
     * Doesn't receive arguments
     */
    public void execute(String argument, OutputHandler outputHandler) {
        outputHandler.print("type: " + CollectionManager.getCollectionType() + "\n" +
                "creation date: " + CollectionManager.getCreationDate() + "\n" +
                "size: " + CollectionManager.getAmountElements() +
                "\n");
    }

    @Override
    public String getDescription() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
