package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import io.network.C2SPackage;

import java.io.IOException;

import static commandmanagement.commands.AddCommand.add;


public class AddIfMinCommand extends Command {

    /**
     * Action for <b>add_if_min</b> command.
     * Receive arguments
     *
     * @param commandData command parameter
     */
    @Override
    public void execute(CommandData commandData) throws IOException {
        commandData.outputHandler().printObj(new C2SPackage("add_if_min", commandData.arg(), add()));
    }

    @Override
    public String getDescription() {
        return "add_if_min {element} : добавить новый элемент в коллекцию, " +
                "если его значение меньше, чем у наименьшего элемента этой коллекции";
    }
}
