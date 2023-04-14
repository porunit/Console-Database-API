package Client.commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import commandmanagement.commands.NoArguments;
import io.network.C2SPackage;

import java.io.IOException;

@NoArguments
public class PrintFieldDescendingCommand extends Command {

    /**
     * Action for <b>print_field_descending_form_of_education</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) throws IOException {
        commandData.outputHandler().printObj(new C2SPackage("print_field_descending_form_of_education", null, null));
    }

    @Override
    public String getDescription() {
        return "print_field_descending_form_of_education : вывести значения поля formOfEducation всех элементов в порядке убывания";
    }
}
