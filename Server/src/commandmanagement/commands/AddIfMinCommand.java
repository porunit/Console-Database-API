package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.CollectionManager;
import io.OutputHandler;
import org.apache.log4j.Logger;

public class AddIfMinCommand extends Command {
    private final Logger log = Logger.getLogger(AddIfMinCommand.class);

    /**
     * Action for <b>add_if_min</b> command.
     * Receive arguments
     *
     * @param commandData command parameter
     */
    @Override
    public void execute(CommandData commandData) {
        long id;
        var argument = commandData.arg();
        OutputHandler outputHandler = commandData.outputHandler();
        try {
            id = Long.parseLong(argument);
        } catch (NumberFormatException e) {
            outputHandler.print("id must be number");
            log.warn("Element didnt insert wrong id format {" + argument + "}");
            return;
        }
        if (id <= 0) outputHandler.print("Id must be bigger then 0");
        else if (CollectionManager.checkId(id)) {
            outputHandler.print("id exists");
            log.warn("Element didnt insert, id exists {" + argument + "}");
        } else if (id < CollectionManager.getMinId() || CollectionManager.getMinId() == 0) {
            var group = commandData.group();
            group.setId(id);
            CollectionManager.add(group);
            outputHandler.print("Element added");
            log.info("Element added {" + group.getId() + "}");
        } else outputHandler.print("Id not min");
    }

    @Override
    public String getDescription() {
        return "add_if_min {element} : добавить новый элемент в коллекцию, " +
                "если его значение меньше, чем у наименьшего элемента этой коллекции";
    }
}
