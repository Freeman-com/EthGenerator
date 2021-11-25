package eth;

import eth.connections.ConnectToNodes;

import java.io.IOException;

public class EthGenerator {
    public static void main(String[] args) throws IOException {
        System.out.println("Main thread started...");
        ConnectToNodes connect = new ConnectToNodes();
        connect.start();

    }
}