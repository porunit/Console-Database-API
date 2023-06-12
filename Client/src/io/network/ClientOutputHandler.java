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
        int SERVER_PORT = 64999;
        serverAddress = new InetSocketAddress("localhost", SERVER_PORT);
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
