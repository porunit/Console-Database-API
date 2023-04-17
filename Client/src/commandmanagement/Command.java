package commandmanagement;

import commandmanagement.commands.NoArguments;

import java.io.IOException;

public abstract class Command {
    public abstract String getDescription();

    public abstract void execute(CommandData commandData) throws IOException;

    public void proxy(CommandData commandData) throws IOException {
        var annotation = this.getClass().getAnnotation(NoArguments.class);
        if (annotation != null && commandData.arg() != null) {
            throw new IllegalArgumentException("Command doesn't receive arguments");
        }
        execute(commandData);
    }
}
