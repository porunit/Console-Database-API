package io.network;


import io.OutputHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerOutputHandler implements OutputHandler {

    DatagramSocket socket;
    InetAddress IPaddress;
    byte[] sendData;
    int PORT;

    public ServerOutputHandler(DatagramSocket socket, InetAddress address) {
        IPaddress = address;
        this.socket = socket;
    }

    public void setPort(int port) {
        PORT = port;
    }

    public void setIP(InetAddress ip) {
        IPaddress = ip;
    }

    @Override
    public void print(String line) {
        S2CPackage s2CPackage = new S2CPackage(line);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(s2CPackage);
            sendData = baos.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPaddress, PORT);
            socket.send(sendPacket);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
