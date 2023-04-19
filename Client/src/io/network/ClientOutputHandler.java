package io.network;

import io.OutputHandler;

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
        int SERVER_PORT = 228;
        serverAddress = new InetSocketAddress("localhost", SERVER_PORT);
    }

    @Override
    public void print(String message) {
        int BUFFER_CAPACITY = 10000;
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
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
