package commandmanagement.commands;


import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.CollectionManager;
import org.apache.log4j.Logger;

public class RemoveByIdCommand extends Command {
    private final Logger log = Logger.getLogger(UpdateCommand.class);

    /**
     * Action for <b>remove</b> command.
     * Receive arguments
     *
     * @param commandData command parameter
     */
    @Override
    public void execute(CommandData commandData) {
        var argument = commandData.arg();
        var outputHandler = commandData.outputHandler();
        long id = 0;
        try {
            id = Long.parseLong(argument);
        } catch (NumberFormatException e) {
            outputHandler.print("id must be number");
            log.warn("Element didnt update wrong id format {" + argument + "}");
        }
        if (CollectionManager.isStackEmpty()) {
            outputHandler.print("Collection is empty");
            log.warn("Element didnt remove, collection empty {" + argument + "}");
        } else if (!CollectionManager.checkId(id)) {
            outputHandler.print("id doesn't exists");
            log.warn("Element didnt remove, id doesnt exists {" + argument + "}");
        } else {
            CollectionManager.remove(id);
            CollectionManager.removeId(id);
            outputHandler.print("Element removed");
            log.info("Element {" + id + "} removed");
        }
    }

    @Override
    public String getDescription() {
        return "remove id {element}: удалить значение элемента коллекции, id которого равен заданному";
    }
}
