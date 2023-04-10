package Client;

import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramChannel channel = DatagramChannel.open();
        InetSocketAddress clientAddress = new InetSocketAddress(224);
        channel.bind(clientAddress);
        ClientInputHandler inputHandler = new ClientInputHandler(channel);
        ClientOutputHandler outputHandler = new ClientOutputHandler(channel);
        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
        String message;
        while (true) {
            System.out.print("Введите команду: ");
            message = consoleInputHandler.input();
            outputHandler.print(message);

            String sb = inputHandler.input();
            System.out.println("Сервер ответил:\n " + sb);
        }
    }
}