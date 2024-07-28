package me;

public class ClientApp {
    public static void main(String[] args) {
        Client client = new Client();
        client.startConnection("localhost", 1234);
        client.connectionLoop();
    }

}
