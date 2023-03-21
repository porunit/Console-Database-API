package io.console;

import io.OutputHandler;

public class ConsoleOutputHandler implements OutputHandler {
    @Override
    public void println(String... line) {
        for (var it : line) {
            System.out.println(it);
        }
    }

    @Override
    public void print(String... line) {
        for (var it : line) {
            System.out.print(it);
        }
    }
}
