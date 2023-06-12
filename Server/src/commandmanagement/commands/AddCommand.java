package commandmanagement.commands;


import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.CollectionManager;
import org.apache.log4j.Logger;

@NoArguments
public class AddCommand extends Command {

    private final Logger log = Logger.getLogger(AddCommand.class);

    /**
     * Creates a new StudyGroup instance based on user input and adds it to the collection
     *
     * @return the newly created StudyGroup instance
     */

    private static long createID() {
        long id = CollectionManager.getAmountElements() + 1;
        while (CollectionManager.checkId(id)) {
            id = id + 1;
        }
        CollectionManager.appendId(id);
        return id;
    }

    /**
     * Action for <b>add</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) {
        var group = commandData.group();
        var id = createID();
        group.setId(id);
        group.setUsername(commandData.username());
        CollectionManager.joinId(id);
        CollectionManager.add(group);
        commandData.outputHandler().print("Element added");
        log.info("Element added {" + group.getId() + "}");
    }

    @Override
    public String getDescription() {
        return "add : добавить новый элемент в коллекцию";
    }
}
