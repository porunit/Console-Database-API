package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.AuthenticationHandler;
import io.network.C2SPackage;

import java.io.IOException;

public class ExecuteScriptCommand extends Command {

    /**
     * Action for <b>execute_script</b> command.
     * Receive arguments
     *
     * @param commandData command parameter
     */
    @Override
    public void execute(CommandData commandData) throws IOException {
        commandData.outputHandler().printObj(new C2SPackage("execute_script", commandData.arg(),AuthenticationHandler.username,AuthenticationHandler.password, null));
    }

    @Override
    public String getDescription() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }
}
