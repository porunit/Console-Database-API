package commandmanagement;

import data.StudyGroup;
import io.OutputHandler;

public record CommandData(
        String arg,
        OutputHandler outputHandler,
        StudyGroup group,
        String username,
        String password
) {
}
