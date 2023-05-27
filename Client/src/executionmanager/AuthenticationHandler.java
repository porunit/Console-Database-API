package executionmanager;

import commandmanagement.CommandData;
import exceptions.AnswerTimeoutException;
import io.ConsoleInputHandler;
import io.network.C2SPackage;
import io.network.ClientInputHandler;
import io.network.ClientOutputHandler;
import io.network.S2CPackage;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthenticationHandler {
    public static String username;
    public static String password;
    private final ConsoleInputHandler inputHandler = new ConsoleInputHandler();
    private final ClientOutputHandler clientOutputHandler;
    private final ClientInputHandler clientInputHandler;

    private boolean login() {
        System.out.println("Enter username");
        String username = inputHandler.input();
        System.out.println("Enter password");
        String password = inputHandler.input();
        try {
            clientOutputHandler.printObj(new C2SPackage("login",null,username,password,null));
            S2CPackage response = clientInputHandler.input();
            System.out.println(response.response());
            if (response.response().equals("Successful")) {
                AuthenticationHandler.username = username;
                AuthenticationHandler.password = password;
                return true;
            }
        } catch (IOException e) {
            System.out.println("Client output error");
        } catch (AnswerTimeoutException | ClassNotFoundException e) {
            System.out.println("Server unavailable");
        }
        return false;
    }

    private boolean signup() {
        System.out.println("Enter username");
        String username = inputHandler.input();
        System.out.println("Enter password");
        String password = inputHandler.input();
        try {
            clientOutputHandler.printObj(new C2SPackage("signup",null,username,password,null));
            S2CPackage response = clientInputHandler.input();
            System.out.println(response.response());
            if (response.response().equals("success")) {
                return true;
            }
        } catch (IOException e) {
            System.out.println("Client output error");
        } catch (AnswerTimeoutException | ClassNotFoundException e) {
            System.out.println("Server unavailable");
        }
        return false;
    }

    public void auth() {
        while (true) {
            System.out.println("LOGIN  - 1\nSIGNUP - 2");
            var answer = inputHandler.input();
            boolean result = false;
            if (answer.equals("1")) {
                result = login();
            } else if (answer.equals("2")) {
                result = signup();
            }
            if (result) {
                return;
            }
        }
    }

}
