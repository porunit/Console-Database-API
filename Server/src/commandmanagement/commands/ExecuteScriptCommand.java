package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import exceptions.RecursionException;
import executionmanager.CommandProcessor;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExecuteScriptCommand extends Command {
    private final Logger log = Logger.getLogger(ExecuteScriptCommand.class);

    public static String checkRecursion(String path, Set<String> used) {
        StringBuilder script = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                script.append(scanner.nextLine()).append('\n');
            }
            Matcher matcher = Pattern.compile("execute_script\\s+(.+)\n", Pattern.MULTILINE).matcher(script);
            while (matcher.find()) {
                String match = matcher.group(1);
                if (used.contains(path)) {
                    return path;
                }
                used.add(path);
                String recursion = checkRecursion(match, new HashSet<>(used));
                if (recursion != null) {
                    return path + " -> " + recursion;
                }
            }
            return null;
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Action for <b>execute_script</b> command.
     * Receive arguments
     *
     * @param commandData command parameter
     */
    @Override
    public void execute(CommandData commandData) {
        var argument = commandData.arg();
        String test = null;
        try {
            test = checkRecursion(argument, new HashSet<>());
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
        if (test != null) {
            throw new RecursionException("Recursion detected: " + test);
        }
        CommandProcessor.executeScript(argument, commandData.outputHandler());
    }

    @Override
    public String getDescription() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
