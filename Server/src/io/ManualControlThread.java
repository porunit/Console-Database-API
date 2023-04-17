package io;

import commandmanagement.Command;
import commandmanagement.CommandData;
import commandmanagement.commands.ExitCommand;
import commandmanagement.commands.SaveCommand;

import java.util.HashMap;
import java.util.Scanner;

public class ManualControlThread implements Runnable {
    HashMap<String, Command> manualCommands;

    public ManualControlThread() {
        manualCommands = new HashMap<>();
        manualCommands.put("exit", new ExitCommand());
        manualCommands.put("save", new SaveCommand());
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите комманду: ");
            String input = scanner.nextLine();
            try {
                parse(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void parse(String commandName) {
        if (commandName == null) return;
        String[] array = commandName.split("\\s+");
        var node = array[0];
        if (array.length > 1) {
            throw new IllegalArgumentException("Too much arguments for command");
        }
        if (!manualCommands.containsKey(commandName)) {
            throw new IllegalArgumentException("Command doesn't exists");
        }
        manualCommands.get(commandName).execute(new CommandData(null, new ConsoleOutputHandler(), null));
    }
}

