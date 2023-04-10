package commandmanagement.commands;

import commandmanagement.Command;
import Client.io.OutputHandler;

@NoArguments
public class ExitCommand extends Command {

    /**
     * Action for <b>exit</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(String argument, OutputHandler outputHandler) {
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}
