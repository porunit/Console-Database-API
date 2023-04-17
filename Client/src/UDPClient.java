import commandmanagement.CommandData;
import commandmanagement.commands.AddCommand;

import executionmanager.CommandProcessor;
import io.ConsoleInputHandler;

import io.network.C2SPackage;
import io.network.ClientInputHandler;
import io.network.ClientOutputHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramChannel channel = DatagramChannel.open();
        InetSocketAddress clientAddress = new InetSocketAddress(224);
        channel.bind(clientAddress);
        channel.configureBlocking(false);
        ClientInputHandler inputHandler = new ClientInputHandler(channel);
        ClientOutputHandler outputHandler = new ClientOutputHandler(channel);
        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_READ);
        while (true) {
            while(true) {
                try {
                    System.out.print("Enter command:");
                    String command = consoleInputHandler.inputCommand();
                    CommandProcessor.parse(command, outputHandler);
                    break;
                }catch (IllegalArgumentException | IOException e){
                    System.out.println(e.getMessage());
                }
            }
            int readyChannels = selector.select(1500);
            if (readyChannels == 0) {
                System.out.println("Server temporary unavailable");
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isReadable()) {
                    System.out.println(inputHandler.input());
                }
            }
        }
    }
}