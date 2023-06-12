package commandmanagement.commands.auth;

import commandmanagement.Command;
import commandmanagement.CommandData;
import exceptions.AnswerTimeoutException;
import io.ConsoleInputHandler;
import io.network.C2SPackage;
import io.network.S2CPackage;

import java.io.IOException;

public class SignUpCommand extends Command {
    private final ConsoleInputHandler inputHandler = new ConsoleInputHandler();

    @Override
    public String getDescription() {
        return "signup: make new user";
    }

    @Override
    public void execute(CommandData commandData) throws IOException {
        var clientOutputHandler = commandData.outputHandler();
        var clientInputHandler = commandData.inputHandler();
        System.out.println("Enter username");
        String username = inputHandler.input();
        System.out.println("Enter password");
        String password = inputHandler.input();
        try {
            clientOutputHandler.printObj(new C2SPackage("signup", null, username, password, null));
            S2CPackage response = clientInputHandler.input();
            System.out.println(response.response());
        } catch (IOException e) {
            System.out.println("Client output error");
        } catch (AnswerTimeoutException | ClassNotFoundException e) {
            System.out.println("Server unavailable");
        }
    }
}
