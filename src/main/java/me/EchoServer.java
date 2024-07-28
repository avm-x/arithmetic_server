package me;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter output;
    private BufferedReader input;

    public void start(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            this.clientSocket = this.serverSocket.accept();

            this.output = new PrintWriter(this.clientSocket.getOutputStream(), true);
            this.input = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

            output.println("Connection Stablished with Arithmetic Echo server");

            while (true) {
                String inputLine = input.readLine();
                if (input.equals("end")) {
                    break;
                }

                output.println("ECHO ; " + inputLine);
            }

            closeAll();
            return;

        } catch (Exception e) {
        }
    }

    public void closeAll() {
        try {
            this.serverSocket.close();
            this.clientSocket.close();
            this.input.close();
            this.output.close();

        } catch (Exception e) {
            System.out.println(e + " Error trying to close connections on EchoServer");
        }
    }
}
