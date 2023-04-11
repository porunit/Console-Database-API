package Client;

import Client.io.AddInputHelper;
import Client.io.network.ClientInputHandler;
import Client.io.network.ClientOutputHandler;
import Client.io.ConsoleInputHandler;
import Client.io.network.NetworkHandler;

import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramChannel channel = DatagramChannel.open();
        InetSocketAddress clientAddress = new InetSocketAddress(224);
        channel.bind(clientAddress);
        ClientInputHandler inputHandler = new ClientInputHandler(channel);
        ClientOutputHandler outputHandler = new ClientOutputHandler(channel);
        NetworkHandler networkHandler =new NetworkHandler(outputHandler);
        ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
        String message;
        while (true) {
            System.out.print("Введите команду: ");
            message = consoleInputHandler.input();
            outputHandler.print(message);
            networkHandler.proxy(message);
            String sb = inputHandler.input();
            System.out.println("Сервер ответил:\n " + sb);
        }
    }
}