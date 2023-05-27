package commandmanagement.commands;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import commandmanagement.Command;
import commandmanagement.CommandData;
import data.StudyGroup;
import exceptions.WrongDataTypeException;
import executionmanager.CollectionManager;
import executionmanager.DatabaseManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Stack;

@NoArguments
public class LoadCommand extends Command {
    private final Logger log = Logger.getLogger(LoadCommand.class);

    /**
     * Action for <b>load</b> command.
     * Doesn't receive arguments
     */
    public void execute(CommandData commandData) {
        var groupStack = DatabaseManager.load();
        CollectionManager.load(groupStack);
        try {
            CollectionManager.joinId();
        } catch (WrongDataTypeException e) {
            log.warn("DB didnt loaded");
            return;
        }
        CollectionManager.sort();
        log.info("DB was loaded");
    }

    @Override
    public String getDescription() {
        return "load path: загрузить коллекцию из файла";
    }

}
