package executionmanager;

import exceptions.AnswerTimeoutException;
import io.ConsoleInputHandler;
import io.network.ClientInputHandler;
import io.network.ClientOutputHandler;
import io.network.S2CPackage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.channels.DatagramChannel;
import java.util.Objects;

public class NetworkHandler {
    static final int PORT_RANGE = 1000;
    static final int DEFAULT_CLIENT_PORT = 3000 + (int) (PORT_RANGE * Math.random());
    private static final Logger log = Logger.getLogger(NetworkHandler.class);

    public static void start() {
        DatagramChannel channel = null;
        try {
            channel = DatagramChannel.open();
        } catch (IOException e) {
            log.error("Channel error" + e.getMessage());
        }
        InetSocketAddress clientAddress = new InetSocketAddress(DEFAULT_CLIENT_PORT);
        try {
            Objects.requireNonNull(channel).bind(clientAddress);
        } catch (IOException e) {
            log.error("Error while setting channel address " + e.getMessage());
        }


        log.info("Chanel opened on port: " + clientAddress);


        assert channel != null;
        ClientInputHandler inputHandler = new ClientInputHandler(channel);
        ClientOutputHandler outputHandler = new ClientOutputHandler(channel);
        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
        AuthenticationHandler authHandler = new AuthenticationHandler(outputHandler, inputHandler);
        authHandler.auth();
        while (true) {
            while (true) {
                try {
                    System.out.print("Enter command: ");
                    String command = consoleInputHandler.inputCommand();
                    CommandProcessor.parse(command, outputHandler, inputHandler);
                    log.debug("Payload (" + command + ") sent to server");
                    break;
                } catch (IllegalArgumentException | IOException | AnswerTimeoutException e) {
                    System.out.println(e.getMessage());
                }
            }
                responseHandling(inputHandler);
        }
    }

    private static void responseHandling(ClientInputHandler inputHandler) {
        try {
            S2CPackage payload = inputHandler.input();
            System.out.println(payload.response());
            log.debug("Payload get from server");
        } catch (IOException | ClassNotFoundException e) {
            log.error(e.getMessage());
        }catch (AnswerTimeoutException e){
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }

    }
}

