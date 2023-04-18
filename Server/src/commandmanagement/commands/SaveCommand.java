package commandmanagement.commands;


import commandmanagement.Command;
import commandmanagement.CommandData;
import data.StudyGroup;
import executionmanager.CollectionManager;
import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

@NoArguments
public class SaveCommand extends Command {
    private final Logger log = Logger.getLogger(SaveCommand.class);
    /**
     * Action for <b>save</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CollectionManager.getFilePath()))) {
            writer.println("---");
            CollectionManager.getAll().stream()
                    .map(StudyGroup::toString)
                    .forEach(writer::println);
            log.info("Data saved to file");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "save : сохранить коллекцию в файл";
    }
}
