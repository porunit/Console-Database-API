package io;


public class ConsoleOutputHandler implements OutputHandler {
    @Override
    public void print(String line) {
        System.out.println(line);
    }
}
