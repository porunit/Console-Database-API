package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.DatabaseManager;
import org.apache.log4j.Logger;

public class LoginCommand extends Command {
    Logger log = Logger.getLogger(LoginCommand.class);
    @Override
    public String getDescription() {
        return "login: войти в аккаунт";
    }

    @Override
    public void execute(CommandData commandData) {
        var name = commandData.username();
        var password = commandData.password();
        var outputHandler = commandData.outputHandler();
        if(!DatabaseManager.checkUser(name)){
            log.debug("User with name: "+name+" doesnt exists");
            outputHandler.print("User with name: "+ name +" doesnt exists");
        }
        if(DatabaseManager.loginUser(name,password)){
            outputHandler.print("Successful");
        }else {
            outputHandler.print("Incorrect password");
        }
    }
}
