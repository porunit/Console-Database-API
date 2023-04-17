package commandmanagement;

import commandmanagement.commands.NoArguments;

public abstract class Command {
    public abstract String getDescription();

    public abstract void execute(CommandData commandData);

    public void proxy(CommandData commandData) {
        var annotation = this.getClass().getAnnotation(NoArguments.class);
        if (annotation != null && commandData.arg() != null) {
            throw new IllegalArgumentException("Command doesn't receive arguments");
        }
        execute(commandData);
    }
}
