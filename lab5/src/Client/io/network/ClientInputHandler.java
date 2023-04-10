package Client.io.network;

import Client.io.InputHandler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientInputHandler implements InputHandler {
    DatagramChannel channel;

    public ClientInputHandler(DatagramChannel chanel) {
        channel = chanel;
    }

    public String input() {
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        StringBuilder receivedMessage = new StringBuilder();
        while (buffer.limit() >= buffer.capacity()) {
            buffer.clear();
            try {
                channel.receive(buffer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            buffer.flip();
            receivedMessage.append(new String(buffer.array(), 0, buffer.limit()));
        }
        return receivedMessage.toString();
    }

    @Override
    public void close() {
        try {
            channel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
