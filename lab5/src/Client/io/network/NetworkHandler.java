package Client.io.network;

import Client.io.AddInputHelper;

import java.util.HashMap;

public class NetworkHandler {
    private final HashMap<String, Signal> signalsMap = new HashMap<>();
    ClientOutputHandler outputHandler;

    public NetworkHandler(ClientOutputHandler outputHandler) {
        this.outputHandler = outputHandler;
        signalsMap.put("add", () -> {
            outputHandler.print(AddInputHelper.add());
        });
        signalsMap.put("update", () -> {
            outputHandler.print(AddInputHelper.add());
        });
        signalsMap.put("exit", () -> {
            System.exit(0);
        });

    }
    public void proxy(String line){
        String[] array = line.split("\\s+");
        String command = array[0];
        if(!signalsMap.containsKey(command)){
            return;
        }
        Signal signal = signalsMap.get(command);
        signal.reaction();
    }

}
