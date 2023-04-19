package io.network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientInputHandler {
    DatagramChannel channel;

    public ClientInputHandler(DatagramChannel chanel) {
        channel = chanel;
    }

    public S2CPackage input() throws IOException, ClassNotFoundException {
        int BUFFER_CAPACITY = 10000;
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
        channel.receive(buffer);
        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return (S2CPackage) is.readObject();
    }

}
