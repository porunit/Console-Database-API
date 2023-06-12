package commandmanagement.commands.auth;

import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.AuthenticationHandler;

import java.io.IOException;

public class LogoutCommand extends Command {
    @Override
    public String getDescription() {
        return "logout";
    }

    @Override
    public void execute(CommandData commandData) throws IOException {
        AuthenticationHandler.password = null;
        AuthenticationHandler.username = null;
        System.out.println("Success");
        AuthenticationHandler.auth();
    }
}
