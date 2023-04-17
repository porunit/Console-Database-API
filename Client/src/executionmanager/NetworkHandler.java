package executionmanager;

import io.ConsoleInputHandler;
import io.network.ClientInputHandler;
import io.network.ClientOutputHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class NetworkHandler {
    static final int PORT_RANGE = 1000;
    static final int DEFAULT_CLIENT_PORT = 3000 + (int) (PORT_RANGE * Math.random());
    static final int TIMEOUT = 1500;
    static final int NO_READY_CHANNELS = 0;

    public static void start() {
        DatagramChannel channel = null;
        try {
            channel = DatagramChannel.open();
        } catch (IOException e) {
            System.out.println("Channel error");
        }
        InetSocketAddress clientAddress = new InetSocketAddress(DEFAULT_CLIENT_PORT);
        try {
            channel.bind(clientAddress);
        } catch (IOException e) {
            System.out.println("Error while setting channel address");
        }
        try {
            channel.configureBlocking(false);
        } catch (IOException e) {
            System.out.println("Error with configure blocking");
        }
        ClientInputHandler inputHandler = new ClientInputHandler(channel);
        ClientOutputHandler outputHandler = new ClientOutputHandler(channel);
        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
        Selector selector = null;
        try {
            selector = Selector.open();
        } catch (IOException e) {
            System.out.println("Error while opening selector");
        }
        try {
            channel.register(selector, SelectionKey.OP_READ);
        } catch (ClosedChannelException e) {
            System.out.println("Error channel already closed");
        }
        while (true) {
            while (true) {
                try {
                    System.out.print("Enter command:");
                    String command = consoleInputHandler.inputCommand();
                    CommandProcessor.parse(command, outputHandler);
                    break;
                } catch (IllegalArgumentException | IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            int readyChannels = 0;
            try {
                readyChannels = selector.select(TIMEOUT);
            } catch (IOException e) {
                System.out.println("Error with input/output");
            }
            if (readyChannels == NO_READY_CHANNELS) {
                System.out.println("Server temporary unavailable");
                continue;
            }
            responseHandling(selector, inputHandler);
        }
    }

    private static void responseHandling(Selector selector, ClientInputHandler inputHandler){
        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            iterator.remove();
            if (key.isReadable()) {
                try {
                    System.out.println(inputHandler.input().response());
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

}
