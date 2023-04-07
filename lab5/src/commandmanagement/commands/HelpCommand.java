package commandmanagement.commands;

import commandmanagement.Command;
import io.OutputHandler;

import java.util.HashMap;

import static commandmanagement.CommandMapsBuilder.buildCommandMap;

@NoArguments
public class HelpCommand extends Command {

    private HashMap<String, Command> commandHashMap;

    /**
     * Action for <b>help</b> command.
     * Doesn't receive arguments
     */
    public void execute(String argument, OutputHandler outputHandler) {
        commandHashMap = buildCommandMap();
        for (var it : commandHashMap.values())
            outputHandler.println(it.getDescription());
    }

    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }
}
