package io.network;

import java.io.Serializable;

public record C2SPackage(
        String command,
        String arg,
        String username,
        String password,
        Serializable group
) implements Serializable {
}
