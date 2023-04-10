package ioserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class ServerInputHandler {
    private static DatagramSocket socket;
    private static int lastPort;



    public static void setSocket(DatagramSocket ServerSocket){
        socket = ServerSocket;
    }
    public static String input() {
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

    public static int getLastPort(){
        return lastPort;
    }

    public void close() {
        socket.close();
    }
}
