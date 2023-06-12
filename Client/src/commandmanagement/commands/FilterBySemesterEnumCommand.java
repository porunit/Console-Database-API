package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import executionmanager.AuthenticationHandler;
import io.network.C2SPackage;

import java.io.IOException;

public class FilterBySemesterEnumCommand extends Command {
    /**
     * Action for <b>filter_by_semester</b> command.
     * Receive arguments
     *
     * @param commandData argument command parameter
     */
    @Override
    public void execute(CommandData commandData) throws IOException {
        commandData.outputHandler().printObj(new C2SPackage("filter_by_semester", commandData.arg(), AuthenticationHandler.username, AuthenticationHandler.password, null));
    }

    @Override
    public String getDescription() {
        return "filter_by_semester semesterEnum : вывести элементы, значение поля semesterEnum которых равно заданному";
    }
}
