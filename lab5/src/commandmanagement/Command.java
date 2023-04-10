package commandmanagement;

import commandmanagement.commands.NoArguments;
import Client.io.OutputHandler;

public abstract class Command {
   public abstract String getDescription();

   public abstract void execute(String argument, OutputHandler handler);

   public void proxy(String argument, OutputHandler outputHandler) {
      var annotation = this.getClass().getAnnotation(NoArguments.class);
      if (annotation != null && argument != null) {
         throw new IllegalArgumentException("Command doesn't receive arguments");
      }
      execute(argument, outputHandler);
   }
}
