package io.network;

import io.InputHandler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientInputHandler{
    DatagramChannel channel;

    public ClientInputHandler(DatagramChannel chanel) {
        channel = chanel;
    }

    public S2CPackage input() throws IOException, ClassNotFoundException {
        byte[] buffer = new byte[10000];
        channel.receive(ByteBuffer.wrap(buffer));
        ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (S2CPackage) ois.readObject();
    }

}
