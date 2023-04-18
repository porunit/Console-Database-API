package io;

import io.network.C2SPackage;

import java.io.IOException;

public interface OutputHandler {
    void print(String line);
    void printObj(C2SPackage command) throws IOException;
}
