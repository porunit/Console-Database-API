package io.console;

import io.InputHandler;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {
    private static final Scanner reader = new Scanner(System.in);

    @Override
    public String input() {
        String string = reader.nextLine();
        if (string == null || string.isBlank() || string.isEmpty())
            return null;
        return string.trim();
    }

    @Override
    public boolean hasNext() {
        return reader.hasNext();
    }

    @Override
    public void close() {
        reader.close();
    }
}
