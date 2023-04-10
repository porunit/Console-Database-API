package Client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientOutputHandler {

    DatagramChannel channel;
    ByteBuffer buffer;
    InetSocketAddress serverAddress;

    public ClientOutputHandler(DatagramChannel channel) {
        this.channel = channel;
        buffer = ByteBuffer.allocate(1024);
        serverAddress = new InetSocketAddress("localhost", 123);
    }
    public void print(String message) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        buffer.put(message.getBytes());
        buffer.flip();
        channel.send(buffer, serverAddress);
        buffer.clear();
    }
}
