package Client.io;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {
    private static final Scanner reader = new Scanner(System.in);

    public String input() {
        while(true) {
            String string = "";
            try {
                string = reader.nextLine();
            } catch (NoSuchElementException e) {
                System.exit(0);
            }
            if (string == null || string.isBlank() || string.isEmpty())
                return null;
            return string.trim();
        }
    }

    @Override
    public void close() {
        reader.close();
    }
}
