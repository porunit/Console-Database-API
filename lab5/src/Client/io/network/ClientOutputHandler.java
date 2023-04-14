package Client.io.network;

import io.OutputHandler;
import io.network.C2SPackage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientOutputHandler implements OutputHandler {

    private final DatagramChannel channel;
    private final InetSocketAddress serverAddress;

    public ClientOutputHandler(DatagramChannel channel) {
        this.channel = channel;
        serverAddress = new InetSocketAddress("localhost", 123);
    }

    @Override
    public void print(String message) {
        ByteBuffer buffer = ByteBuffer.allocate(10000);
        buffer.put(message.getBytes());
        buffer.flip();
        try {
            channel.send(buffer, serverAddress);
        } catch (IOException e) {
            System.err.println("IO");
        }
        buffer.clear();
    }
    @Override
    public void printObj(C2SPackage command) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(command);
        byte[] data = baos.toByteArray();
        ByteBuffer buffer = ByteBuffer.wrap(data);
        channel.send(buffer, serverAddress);
    }

}
