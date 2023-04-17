package commandmanagement;

import io.OutputHandler;

public record CommandData(
       String arg,
       OutputHandler outputHandler
) {}
