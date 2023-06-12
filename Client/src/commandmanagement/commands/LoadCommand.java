package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.AuthenticationHandler;
import io.network.C2SPackage;

import java.io.IOException;

@NoArguments
public class LoadCommand extends Command {

    /**
     * Action for <b>load</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) throws IOException {
        commandData.outputHandler().printObj(new C2SPackage("load",  null, AuthenticationHandler.username,AuthenticationHandler.password,null));
    }

    @Override
    public String getDescription() {
        return "load: загрузить коллекцию из файла";
    }

}
