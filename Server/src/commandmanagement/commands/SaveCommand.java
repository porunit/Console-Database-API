package commandmanagement.commands;


import commandmanagement.Command;
import commandmanagement.CommandData;
import data.StudyGroup;
import executionmanager.CollectionManager;
import executionmanager.DatabaseManager;
import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@NoArguments
public class SaveCommand extends Command {
    private final Logger log = Logger.getLogger(SaveCommand.class);

    /**
     * Action for <b>save</b> command.
     * Doesn't receive arguments
     */
    @Override
    public void execute(CommandData commandData) {
        try {
            DatabaseManager.save(CollectionManager.getAll());
            commandData.outputHandler().print("Successful");
        }catch (SQLException e){
            commandData.outputHandler().print(e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "save : сохранить коллекцию в файл";
    }
}
