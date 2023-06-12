package executionmanager;

import data.StudyGroup;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Stack;

public class DatabaseManager {
    private static final Logger log = Logger.getLogger(DatabaseManager.class);
    private static DatabaseHandler dbHandler;

    public static Stack<StudyGroup> load() {
        var stack = new Stack<StudyGroup>();
        stack.addAll(dbHandler.getAllStudyGroups());
        return stack;
    }

    public static void setDbHandler(DatabaseHandler dbHandler) {
        DatabaseManager.dbHandler = dbHandler;
    }

    public static boolean checkUser(String name) {
        return dbHandler.checkUsername(name);
    }

    public static boolean loginUser(String name, String password) {
        return dbHandler.loginUser(name, password);

    }
    public static boolean registerUser(String name, String password) {
        return dbHandler.registerUser(name, password);
    }

    public static void save(List<StudyGroup> groups) throws SQLException {
        dbHandler.save(groups);
        log.debug(groups.size() + " groups were save");
    }
}
