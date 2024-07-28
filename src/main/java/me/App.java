package me;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        EchoServer server = new EchoServer();
        server.start(1234);
    }
}
