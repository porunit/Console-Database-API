package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.AuthenticationHandler;
import io.network.C2SPackage;

import java.io.IOException;

@NoArguments
public class ShowCommand extends Command {

    /**
     * Action for <b>show</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) {
        try {
            C2SPackage payload = new C2SPackage("show", commandData.arg(), AuthenticationHandler.username,AuthenticationHandler.password,null);
            commandData.outputHandler().printObj(payload);
        } catch (IOException e) {
            System.out.println("IOshow");
        }
    }

    @Override
    public String getDescription() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
