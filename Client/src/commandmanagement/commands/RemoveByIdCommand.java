package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.AuthenticationHandler;
import io.network.C2SPackage;

import java.io.IOException;

public class RemoveByIdCommand extends Command {

    /**
     * Action for <b>remove</b> command.
     * Receive arguments
     *
     * @param commandData command parameter
     */
    @Override
    public void execute(CommandData commandData) throws IOException {
        commandData.outputHandler().printObj(new C2SPackage("remove", commandData.arg(), AuthenticationHandler.username,AuthenticationHandler.password,null));
    }

    @Override
    public String getDescription() {
        return "remove id {element}: удалить значение элемента коллекции, id которого равен заданному";
    }
}
