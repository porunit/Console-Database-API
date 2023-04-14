package Client.commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import commandmanagement.commands.NoArguments;
import io.network.C2SPackage;

import java.io.IOException;
import java.io.Serializable;

@NoArguments
public class InfoCommand extends Command implements Serializable {

    /**
     * Action for <b>info</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) throws IOException {
        commandData.outputHandler().printObj(new C2SPackage("info", null, null));
    }

    @Override
    public String getDescription() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
