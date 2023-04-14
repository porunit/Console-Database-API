package commandmanagement.commands;

import commandmanagement.Command;
import commandmanagement.CommandData;
import data.FormOfEducation;
import data.comparators.FormOfEducationComparator;
import executionmanager.CollectionManager;

import java.util.ArrayList;

@NoArguments
public class PrintFieldDescendingCommand extends Command {

    /**
     * Action for <b>print_field_descending_form_of_education</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) {
        var groups = CollectionManager.getAll();
        ArrayList<FormOfEducation> formOfEducations = new ArrayList<>();
        for (var it : groups) {
            formOfEducations.add(it.getFormOfEducation());
        }
        formOfEducations.sort(new FormOfEducationComparator());
        StringBuilder builder = new StringBuilder();
        for (var it : formOfEducations) {
            builder.append(it).append(" | ");
        }
        commandData.outputHandler().print(builder.toString());
    }

    @Override
    public String getDescription() {
        return "print_field_descending_form_of_education : вывести значения поля formOfEducation всех элементов в порядке убывания";
    }
}
