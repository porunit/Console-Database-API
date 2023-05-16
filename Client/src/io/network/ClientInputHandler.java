package io.network;

import exceptions.AnswerTimeoutException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class ClientInputHandler {
    private static final int BUFFER_CAPACITY = 10000;
    private static final int TIMEOUT_MS = 10000;

    private final DatagramChannel channel;

    public ClientInputHandler(DatagramChannel channel) {
        this.channel = channel;
        try {
            channel.configureBlocking(true);
            channel.socket().setSoTimeout(TIMEOUT_MS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public S2CPackage input() throws IOException, ClassNotFoundException, AnswerTimeoutException {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
        channel.configureBlocking(false);

        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_READ);

        if (selector.select(TIMEOUT_MS) == 0) {
            throw new AnswerTimeoutException("Timeout while waiting for server response");
        }

        Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
        while (keyIterator.hasNext()) {
            SelectionKey key = keyIterator.next();
            if (key.isReadable()) {
                channel.receive(buffer);
                break;
            }
            keyIterator.remove();
        }

        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return (S2CPackage)is.readObject();
    }
}

