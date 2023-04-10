package executionmanager;

import commandmanagement.Command;
import commandmanagement.CommandMapsBuilder;

import exceptions.RecursionException;
import Client.io.OutputHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class CommandProcessor {
    final static int COMMAND_NO_ARG_LENGTH = 1;
    final static int COMMAND_MAX_ARG_LENGTH = 2;
    private static final HashMap<String, Command> commandsHashMap = CommandMapsBuilder.buildCommandMap();

    /**
     * Parses the command input string and executes the corresponding command. The method first trims the input string and
     * splits it into an array of strings separated by whitespace. The first element of the array is checked if it is a
     * valid command. If not, an error message is printed. If it is a command without argument, and the array length is not
     * equal to the expected length, another error message is printed. If it is a command without argument and the length is
     * correct, the corresponding command is executed. If it is a command with argument, the array length is checked, and if
     * it is not equal to 2 or the second argument is null or empty, an error message is printed. Otherwise, the second
     * element of the array is retrieved as the argument and the corresponding command is executed with that argument.
     *
     * @param commandName a string representing the command input to parse
     */
    public static void parse(String commandName, OutputHandler outputHandler) {
        try {
            if (commandName == null) return;
            String[] array = commandName.split("\\s+");
            var node = array[0];
            if (array.length > COMMAND_MAX_ARG_LENGTH) {
                throw new IllegalArgumentException("Too much arguments for command");
            }
            if (!isCommand(node)) {
                outputHandler.print("Command doesn't exists");
            } else if (array.length == COMMAND_NO_ARG_LENGTH) {
                Command command = commandsHashMap.get(node);
                command.proxy(null, outputHandler);
            } else {
                String argument = array[1];
                Command command = commandsHashMap.get(node);
                command.proxy(argument, outputHandler);
            }
        } catch (IllegalArgumentException | RecursionException e) {
            outputHandler.print(e.getMessage());
        }

    }

    private static boolean isCommand(String command) {
        return commandsHashMap.containsKey(command);
    }

    /**
     * Reads liens from script.txt file with commands and parse them one by one with  parse() method. If path is incorrect,
     * error message is printed. If is recursion in script, then if it loops, another error message is printed.
     *
     * @param path file name or location
     */
    public static void executeScript(String path, OutputHandler outputHandler) {
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                parse(String.valueOf(scanner.nextLine()), outputHandler);
            }
        } catch (FileNotFoundException e) {
            outputHandler.print("File not found exception");
        } catch (StackOverflowError e) {
            outputHandler.print("Recursion script error");
        }
    }
}
