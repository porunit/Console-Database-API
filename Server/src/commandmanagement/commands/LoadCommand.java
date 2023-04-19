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
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

@NoArguments
public class LoadCommand extends Command {
    private final Logger log = Logger.getLogger(LoadCommand.class);

    /**
     * Action for <b>load</b> command.
     * Doesn't receive arguments
     */
    public void execute(CommandData commandData) {
        var outputHandler = commandData.outputHandler();
        String path = CollectionManager.getFilePath();
        Stack<StudyGroup> groupStack;
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            groupStack = mapper.readValue(new File(path), new TypeReference<Stack<StudyGroup>>() {
            });
        } catch (DatabindException e) {
            outputHandler.print("'" + path + "' contains broken data");
            log.warn("File didnt loaded '" + path + "' contains broken data");
            return;
        } catch (IOException e) {
            outputHandler.print("Unable to load '" + path + "' No such file\n");
            log.warn("File didnt loaded'\" + path + \"' No such file");
            return;
        }
        CollectionManager.load(groupStack);
        try {
            CollectionManager.joinId();
        } catch (WrongDataTypeException e) {
            outputHandler.print("ID's must be different");
            log.warn("File didnt loaded: wrong id");
            return;
        }
        CollectionManager.sort();
        outputHandler.print("File was loaded");
        log.info("File was loaded: " + path);
    }

    @Override
    public String getDescription() {
        return "load path: загрузить коллекцию из файла";
    }

}
