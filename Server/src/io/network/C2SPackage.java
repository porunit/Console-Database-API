package io.network;

import java.io.Serializable;

public record C2SPackage(
        String command,
        String arg,
        Serializable group
) implements Serializable {
}
