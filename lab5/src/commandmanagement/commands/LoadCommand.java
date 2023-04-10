package commandmanagement.commands;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import commandmanagement.Command;
import data.StudyGroup;
import exceptions.WrongDataTypeException;
import executionmanager.CollectionManager;
import Client.io.OutputHandler;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

@NoArguments
public class LoadCommand extends Command {

    /**
     * Action for <b>load</b> command.
     * Doesn't receive arguments
     */
    public void execute(String argument, OutputHandler outputHandler) {
        String path = CollectionManager.getFilePath();
        Stack<StudyGroup> groupStack = new Stack<>();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            groupStack = mapper.readValue(new File(path), new TypeReference<Stack<StudyGroup>>() {
            });
            outputHandler.print("File was loaded");
        } catch (DatabindException e) {
            outputHandler.print("'" + path + "' contains broken data");
        } catch (IOException e) {
            outputHandler.print("Unable to load '" + path + "' No such file\n");
        }
        CollectionManager.load(groupStack);
        try {
            CollectionManager.joinId();
        } catch (WrongDataTypeException e) {
            outputHandler.print("ID's must be different");
        }
        CollectionManager.sort();
    }

    @Override
    public String getDescription() {
        return "load path: загрузить коллекцию из файла";
    }

}
