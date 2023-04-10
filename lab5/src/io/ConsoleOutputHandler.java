package io;

import Client.io.OutputHandler;

public class ConsoleOutputHandler implements OutputHandler {

    public void println(String line) {
            System.out.println(line);
    }

    @Override
    public void print(String line) {
        System.out.print(line);
    }
}
