package io;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleInputHandler {
    private static final Scanner reader = new Scanner(System.in);


    public String inputCommand() {
        String string = null;
        while (string == null || string.isBlank() || string.isEmpty()) {
            try {
                string = reader.nextLine();
            } catch (NoSuchElementException e) {
                System.exit(0);
            }
        }
        return string.trim();
    }

    public String input() {
        String string = "unknown command";
        try {
            string = reader.nextLine();
        } catch (NoSuchElementException e) {
            System.exit(0);
        }
        if (string == null || string.isBlank() || string.isEmpty())
            return null;
        return string.trim();

    }
}
