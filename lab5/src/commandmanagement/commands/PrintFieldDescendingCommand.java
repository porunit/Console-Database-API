package commandmanagement.commands;

import commandmanagement.Command;
import data.comparators.FormOfEducationComparator;
import data.FormOfEducation;
import executionmanager.CollectionManager;
import io.OutputHandler;

import java.util.ArrayList;

@NoArguments
public class PrintFieldDescendingCommand extends Command {

    /**
     * Action for <b>print_field_descending_form_of_education</b> command.
     * Doesn't receive arguments
     */
    public void execute(String argument, OutputHandler outputHandler) {
        var groups = CollectionManager.getAll();
        ArrayList<FormOfEducation> formOfEducations = new ArrayList<>();
        for (var it : groups) {
            formOfEducations.add(it.getFormOfEducation());
        }
        formOfEducations.sort(new FormOfEducationComparator());
        for (var it : formOfEducations) {
            System.out.print(it + " | ");
        }
        outputHandler.println();
    }

    @Override
    public String getDescription() {
        return "print_field_descending_form_of_education : вывести значения поля formOfEducation всех элементов в порядке убывания";
    }
}
