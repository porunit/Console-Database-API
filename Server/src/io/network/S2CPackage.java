package io.network;

import java.io.Serializable;

public record S2CPackage(
        String response
) implements Serializable {
}
