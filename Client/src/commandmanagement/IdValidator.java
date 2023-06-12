package commandmanagement;

import exceptions.AnswerTimeoutException;
import executionmanager.AuthenticationHandler;
import io.OutputHandler;
import io.network.C2SPackage;
import io.network.ClientInputHandler;
import io.network.ClientOutputHandler;
import io.network.S2CPackage;

import java.io.IOException;


public class IdValidator {
    private final ClientInputHandler inputHandler;
    private final OutputHandler outputHandler;

    public IdValidator(ClientInputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public boolean validateId(String id) throws AnswerTimeoutException {
        try {
            outputHandler.printObj(new C2SPackage("validate", id, AuthenticationHandler.username,AuthenticationHandler.password,null));
            S2CPackage payload = inputHandler.input();
            if (payload.response().equals("approve")) {
                return true;
            }
        } catch (IOException ignored) {
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
