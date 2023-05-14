package commandmanagement.commands;

import executionmanager.CollectionManager;
import io.network.C2SPackage;

import io.network.S2CPackage;
import io.network.ServerInputHandler;
import io.network.ServerOutputHandler;

import java.io.IOException;


public class IdValidator {
    private final ServerInputHandler inputHandler;
    private final ServerOutputHandler outputHandler;

    public IdValidator(ServerInputHandler inputHandler, ServerOutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public boolean validateId(String id) {
        outputHandler.setIP(inputHandler.getLastIP());
        outputHandler.setPort(inputHandler.getLastPort());
        if (CollectionManager.checkId(Long.parseLong(id))) {
            outputHandler.print("approve");
            return true;
        }
        outputHandler.print("reject");
        return false;
    }
}