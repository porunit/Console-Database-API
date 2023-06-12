package commandmanagement;

import io.OutputHandler;
import io.network.ClientInputHandler;

public record CommandData(
        String arg,
        OutputHandler outputHandler,
        ClientInputHandler inputHandler
) {
}
