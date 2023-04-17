package commandmanagement.commands;


import commandmanagement.Command;
import commandmanagement.CommandData;
import data.StudyGroup;
import executionmanager.CollectionManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@NoArguments
public class SaveCommand extends Command {

    /**
     * Action for <b>save</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) {
        var outputHandler = commandData.outputHandler();
        try (FileWriter writer = new FileWriter(CollectionManager.getFilePath());
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write("---\n");
            var groups = CollectionManager.getAll();
            for (StudyGroup group : groups) {
                bw.write(group.toString() + "\n");
            }
            outputHandler.print("Text written to the file successfully.");
        } catch (IOException e) {
            outputHandler.print("IOException catch, it may be due to lack of file permissions");
        }
    }

    @Override
    public String getDescription() {
        return "save : сохранить коллекцию в файл";
    }
}