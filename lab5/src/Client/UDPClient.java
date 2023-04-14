package Client;

import executionmanager.CommandProcessor;
import io.ConsoleInputHandler;
import io.network.ClientInputHandler;
import io.network.ClientOutputHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
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
                    String command = consoleInputHandler.input();
                    CommandProcessor.parse(command, outputHandler);
                    break;
                }catch (IllegalArgumentException | IOException e){
                    System.out.println(e.getMessage());
                }
            }
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                // Удаляем текущий ключ из выбранных ключей
                iterator.remove();
                // Если ключ доступен для чтения, читаем данные в буфер и выводим их на экран
                if (key.isReadable()) {
                    System.out.println(inputHandler.input());
                }
            }
        }
    }
}