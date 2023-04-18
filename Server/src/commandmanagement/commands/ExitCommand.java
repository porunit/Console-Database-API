package commandmanagement.commands;


import commandmanagement.Command;
import commandmanagement.CommandData;
import org.apache.log4j.Logger;

@NoArguments
public class ExitCommand extends Command {
    private final Logger log = Logger.getLogger(ExitCommand.class);
    /**
     * Action for <b>exit</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) {
        log.info("Server shut down");
        System.exit(0);

    }

    @Override
    public String getDescription() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}
