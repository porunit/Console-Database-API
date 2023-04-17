import executionmanager.CommandProcessor;
import executionmanager.NetworkHandler;
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
        NetworkHandler.start();
    }
}