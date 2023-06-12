package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.AuthenticationHandler;
import io.network.C2SPackage;

import java.io.IOException;

@NoArguments
public class ClearCommand extends Command {

    /**
     * Action for <b>clear</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) throws IOException {
        commandData.outputHandler().printObj(new C2SPackage("clear", null, AuthenticationHandler.username,AuthenticationHandler.password,null));
    }

    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }
}
