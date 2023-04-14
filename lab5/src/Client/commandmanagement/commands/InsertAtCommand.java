package Client.commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import io.network.C2SPackage;

import java.io.IOException;

public class InsertAtCommand extends Command {
    /**
     * Action for <b>insert_at</b> command.
     * Receive arguments
     *
     * @param commandData argument command parameter
     */
    @Override
    public void execute(CommandData commandData) throws IOException {
        commandData.outputHandler().printObj(new C2SPackage("info", commandData.arg(), null));
    }

    @Override
    public String getDescription() {
        return "insert_at index {element} : добавить новый элемент в заданную позицию";
    }
}
