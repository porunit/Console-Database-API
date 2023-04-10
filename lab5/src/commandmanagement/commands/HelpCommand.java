package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandMapsBuilder;
import io.OutputHandler;

import java.util.HashMap;

@NoArguments
public class HelpCommand extends Command {


    /**
     * Action for <b>help</b> command.
     * Doesn't receive arguments
     */
    public void execute(String argument, OutputHandler outputHandler) {
        HashMap<String, Command> commandHashMap = CommandMapsBuilder.buildCommandMap();
        StringBuilder builder = new StringBuilder();
        for (var it : commandHashMap.values())
            builder.append(it.getDescription()).append("\n");
        outputHandler.println(builder.toString());
    }

    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }
}
