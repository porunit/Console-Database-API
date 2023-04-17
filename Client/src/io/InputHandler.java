package io;

import java.io.IOException;

public interface InputHandler {
    String input() throws IOException, ClassNotFoundException;

    void close();
}
