package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.AuthenticationHandler;
import io.network.C2SPackage;

import java.io.IOException;

@NoArguments
public class PrintDescendingCommand extends Command {

    /**
     * Action for <b>print_descending</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) throws IOException {
        commandData.outputHandler().printObj(new C2SPackage("print_descending", null, AuthenticationHandler.username,AuthenticationHandler.password,null));
    }

    @Override
    public String getDescription() {
        return "print_descending : вывести элементы коллекции в порядке убывания";
    }
}
