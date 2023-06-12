package commandmanagement.commands;


import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.CollectionManager;
import org.apache.log4j.Logger;

import java.util.Objects;

public class UpdateCommand extends Command {
    private final Logger log = Logger.getLogger(UpdateCommand.class);

    /**
     * Action for <b>update</b> command.
     * Receives arguments
     *
     * @param commandData command parameter
     */
    @Override
    public void execute(CommandData commandData) {
        var argument = commandData.arg();
        var outputHandler = commandData.outputHandler();
        long id;
        try {
            id = Long.parseLong(argument);
        } catch (NumberFormatException e) {
            outputHandler.print("id must be number");
            log.warn("Element didnt update, wrong id format {" + argument + "}");
            return;
        }
        if (CollectionManager.isStackEmpty()) {
            outputHandler.print("Collection is empty");
            log.warn("Element didnt update, empty collection {" + argument + "}");
        } else if (!CollectionManager.checkId(id)) {
            outputHandler.print("id doesn't exist");
            log.warn("Element didnt update, id doesnt exist {" + argument + "}");
        } else {
            var group = commandData.group();
            group.setId(id);
            var studyGroup = CollectionManager.getById(id);
            if(!Objects.equals(studyGroup.getUsername(), commandData.username())){
                outputHandler.print("You dont have access");
                return;
            }
            group.setUsername(commandData.username());
            CollectionManager.remove(id);
            CollectionManager.add(group);
            outputHandler.print("Element updated");
            log.info("Element {" + id + "} updated");
        }
    }
    @Override
    public String getDescription() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}

