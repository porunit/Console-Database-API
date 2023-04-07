package commandmanagement.commands;

import commandmanagement.Command;
import executionmanager.CollectionManager;
import io.OutputHandler;

public class AddIfMinCommand extends Command {

    /**
     * Action for <b>add_if_min</b> command.
     * Receive arguments
     *
     * @param argument command parameter
     */
    @Override
    public void execute(String argument, OutputHandler outputHandler) {
        long id;
        try {
            id = Long.parseLong(argument);
        } catch (NumberFormatException e) {
            System.out.println("id must be number");
            return;
        }
        if (id <= 0) System.out.println("Id must be bigger then 0");
        else if (CollectionManager.checkId(id)) System.out.println("id exists");
        else if (id < CollectionManager.getMinId()) {
            var group = AddCommand.add();
            group.setId(id);
            CollectionManager.add(group);
        } else System.out.println("Id not min");
    }

    @Override
    public String getDescription() {
        return "add_if_min {element} : добавить новый элемент в коллекцию, " +
                "если его значение меньше, чем у наименьшего элемента этой коллекции";
    }
}
