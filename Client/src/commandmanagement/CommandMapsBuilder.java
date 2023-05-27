package commandmanagement;

import commandmanagement.commands.*;

import java.util.HashMap;

public class CommandMapsBuilder {

    /**
     * Creates Hashmap with all commands which <b>contains</b> argument. Where keys - command names and items - command classes.
     *
     * @return Hashmap with String keys and CommandWithArgument items
     */
    public static HashMap<String, Command> buildCommandMap() {
        HashMap<String, Command> commandHashMap = new HashMap<>();
        commandHashMap.put("add_if_min", new AddIfMinCommand());
        commandHashMap.put("filter_by_semester", new FilterBySemesterEnumCommand());
        commandHashMap.put("insert_at", new InsertAtCommand());
        commandHashMap.put("remove", new RemoveByIdCommand());
        commandHashMap.put("update", new UpdateCommand());
        commandHashMap.put("execute_script", new ExecuteScriptCommand());
        commandHashMap.put("exit", new ExitCommand());
        commandHashMap.put("clear", new ClearCommand());
        commandHashMap.put("add", new AddCommand());
        commandHashMap.put("help", new HelpCommand());
        commandHashMap.put("info", new InfoCommand());
     //   commandHashMap.put("load", new LoadCommand());
        commandHashMap.put("print_descending", new PrintDescendingCommand());
        commandHashMap.put("print_field_descending_form_of_education", new PrintFieldDescendingCommand());
        commandHashMap.put("show", new ShowCommand());
        return commandHashMap;
    }
}
