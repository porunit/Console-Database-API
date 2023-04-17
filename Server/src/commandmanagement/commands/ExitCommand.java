package commandmanagement.commands;


import commandmanagement.Command;
import commandmanagement.CommandData;

@NoArguments
public class ExitCommand extends Command {

    /**
     * Action for <b>exit</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) {
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}
