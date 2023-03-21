import executionmanager.CommandPrompt;
import io.console.ConsoleInputHandler;
import io.console.ConsoleOutputHandler;

public class Main {
    public static void main(String[] args) {
        var inputHandler = new ConsoleInputHandler();
        var outputHandler = new ConsoleOutputHandler();
        CommandPrompt commandPrompt = new CommandPrompt(inputHandler, outputHandler);
        commandPrompt.start();
    }
}