package ioserver;

import io.InputHandler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class ServerInputHandler implements InputHandler {
    private final DatagramSocket socket;
    private int lastPort;

    public ServerInputHandler(DatagramSocket socket){
        this.socket = socket;
    }

    @Override
    public String input() {
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            socket.receive(receivePacket);
        }catch (IOException e){
            System.err.println("IO error");
        }
        String command = new String(receivePacket.getData(), 0, receivePacket.getLength());
        lastPort = receivePacket.getPort();
        return command.trim();
    }

    public int getLastPort(){
        return lastPort;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public void close() {
        socket.close();
    }
}
