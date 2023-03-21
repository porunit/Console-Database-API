package executionmanager;

import commandmanagement.commands.LoadCommand;
import io.InputHandler;
import io.OutputHandler;


import java.util.NoSuchElementException;

public class CommandPrompt {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public CommandPrompt(InputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    /**
     * This method starts the console-based user interface for interacting with the study group collection.
     * It first tries to load the collection from the file using LoadCommand.
     * Then it enters a loop where it prompts the user for commands until the collection is loaded and the user
     * can enter commands. The method reads user input and passes it to the parse method of the Parser object for processing.
     * If an error occurs while loading or reading user input, an error message is printed and the method exits.
     */
    public void start() {
        String command;
        boolean isLoaded;
        try {
            new LoadCommand().execute(null, outputHandler);
        } catch (NoSuchFieldError e) {
            outputHandler.println("Error While loading");
        } finally {
            isLoaded = !CollectionManager.isStackEmpty();
        }
        while (isLoaded) {
            outputHandler.print("Enter command(type help to see command list): ");
            try {
                command = inputHandler.input();
            } catch (NoSuchElementException e) {
                break;
            }
            parser.parse(command);
        }
    }
}
