package commandmanagement.commands;


import commandmanagement.Command;
import commandmanagement.CommandData;
import commandmanagement.CommandMapsBuilder;

import java.util.HashMap;

@NoArguments
public class HelpCommand extends Command {


    /**
     * Action for <b>help</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) {

        HashMap<String, Command> commandHashMap = CommandMapsBuilder.buildCommandMap();
        StringBuilder builder = new StringBuilder();
        for (var it : commandHashMap.values())
            builder.append(it.getDescription()).append("\n");
        commandData.outputHandler().print(builder.toString());
    }

    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }
}
