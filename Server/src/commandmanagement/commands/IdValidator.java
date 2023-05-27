package commandmanagement.commands;

import executionmanager.CollectionManager;
import io.network.C2SPackage;

import io.network.S2CPackage;
import io.network.ServerInputHandler;
import io.network.ServerOutputHandler;

import java.io.IOException;


public class IdValidator {
    private final ServerOutputHandler outputHandler;

    public IdValidator(ServerOutputHandler outputHandler) {
        this.outputHandler = outputHandler;
    }

    public boolean validateId(String id) {
        if (CollectionManager.checkId(Long.parseLong(id))) {
            outputHandler.print("approve");
            return true;
        }
        outputHandler.print("reject");
        return false;
    }
}