package executionmanager;

import commandmanagement.CommandData;
import commandmanagement.commands.auth.LoginCommand;
import commandmanagement.commands.auth.SignUpCommand;
import exceptions.AnswerTimeoutException;
import io.ConsoleInputHandler;
import io.network.C2SPackage;
import io.network.ClientInputHandler;
import io.network.ClientOutputHandler;
import io.network.S2CPackage;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.IOException;


@RequiredArgsConstructor
public class AuthenticationHandler {
    public static String username;
    public static String password;
    private static final ConsoleInputHandler inputHandler;
    @Setter
    private static ClientOutputHandler clientOutputHandler;
    @Setter
    private static ClientInputHandler clientInputHandler;
    static {
        inputHandler = new ConsoleInputHandler();
    }


    public static void auth() {
        while (true) {
            System.out.println("LOGIN  - 1\nSIGNUP - 2");
            var answer = inputHandler.input();
            try {
                if (answer.equals("1")) {
                    new LoginCommand().execute(new CommandData(null, clientOutputHandler, clientInputHandler));
                } else if (answer.equals("2")) {
                    new SignUpCommand().execute(new CommandData(null, clientOutputHandler, clientInputHandler));
                }
            }catch (IOException e){
                System.out.println("Error while handling request");
            }
            if (username!=null) {
                return;
            }
        }
    }

}
