package commandmanagement.commands;


import commandmanagement.Command;
import commandmanagement.CommandData;
import data.StudyGroup;
import executionmanager.CollectionManager;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@NoArguments
public class SaveCommand extends Command {
    private final Logger log = Logger.getLogger(SaveCommand.class);
    /**
     * Action for <b>save</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) {
        try (FileWriter writer = new FileWriter(CollectionManager.getFilePath());
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write("---\n");
            var groups = CollectionManager.getAll();
            for (StudyGroup group : groups) {
                bw.write(group.toString() + "\n");
            }
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
