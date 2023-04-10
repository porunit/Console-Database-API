package ioserver;

import io.OutputHandler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerOutputHandler implements OutputHandler {

    DatagramSocket socket;
    InetAddress IPaddress;
    byte[] sendData;
    int PORT;

    public ServerOutputHandler(DatagramSocket socket, InetAddress address){
        IPaddress = address;
        this.socket = socket;
    }
    @Override
    public void println(String line) {
        sendData = line.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPaddress, PORT);
        try {
            socket.send(sendPacket);
        }catch (IOException e){
            System.err.println("IO");
        }
    }
    public void setPort(int port){
        PORT = port;
    }
    @Override
    public void print(String line) {
        sendData = line.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPaddress, PORT);
        try {
            socket.send(sendPacket);
        }catch (IOException e){
            System.err.println("IO");
        }
    }
}
