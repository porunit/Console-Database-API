package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.DatabaseManager;
import org.apache.log4j.Logger;

public class SignInCommand extends Command {
    Logger log = Logger.getLogger(SignInCommand.class);
    @Override
    public String getDescription() {
        return "signup: зарегистрировать новый аккаунт";
    }

    @Override
    public void execute(CommandData commandData) {
        var name = commandData.username();
        var password = commandData.password();
        var outputHandler = commandData.outputHandler();
        if(DatabaseManager.checkUser(name)){
            log.debug("User with name: "+name+" already exists");
            outputHandler.print("User with name: "+ name +" already exists");
            return;
        }
        if(DatabaseManager.registerUser(name, password)){
            outputHandler.print("Successful");
        }
    }
}
