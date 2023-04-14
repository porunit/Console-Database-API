package Client.commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import commandmanagement.commands.NoArguments;
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
        commandData.outputHandler().printObj(new C2SPackage("load", null, null));
    }

    @Override
    public String getDescription() {
        return "load path: загрузить коллекцию из файла";
    }

}
