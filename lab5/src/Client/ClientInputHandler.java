package Client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientInputHandler {
    ByteBuffer buffer;
   DatagramChannel channel;
   public ClientInputHandler(DatagramChannel chanel){
       channel = chanel;
       buffer = ByteBuffer.allocate(4096);
   }

   public String input() throws IOException {
       StringBuilder receivedMessage = new StringBuilder();
       while (buffer.limit() >= buffer.capacity()) {
           buffer.clear();
           channel.receive(buffer);
           buffer.flip();
           receivedMessage.append(new String(buffer.array(), 0, buffer.limit()));
       }
       return receivedMessage.toString();
   }
}
