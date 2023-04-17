package io.network;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class ServerInputHandler {
    private final DatagramSocket socket;
    private int lastPort;
    private InetAddress lastIP;


    public ServerInputHandler(DatagramSocket ServerSocket) {
        socket = ServerSocket;
    }

    public C2SPackage input() throws IOException, ClassNotFoundException {
        byte[] buffer = new byte[10000];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        lastPort = packet.getPort();
        lastIP = packet.getAddress();
        byte[] data = packet.getData();
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return (C2SPackage) ois.readObject();
    }

    public InetAddress getLastIP() {
        return lastIP;
    }

    public int getLastPort() {
        return lastPort;
    }

}
