package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.AuthenticationHandler;
import io.network.C2SPackage;

import java.io.IOException;

import static commandmanagement.commands.AddCommand.add;

public class UpdateCommand extends Command {
    /**
     * Action for <b>update</b> command.
     * Receives arguments
     *
     * @param commandData command parameter
     */
    @Override
    public void execute(CommandData commandData) throws IOException {
        commandData.outputHandler().printObj(new C2SPackage("update", commandData.arg(), AuthenticationHandler.username, AuthenticationHandler.password, add()));
    }

    @Override
    public String getDescription() {
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }
}

