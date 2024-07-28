package me;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
    private Socket clientSocket;
    private PrintWriter output;
    private BufferedReader input;

    public Client() {
    }

    public void startConnection(String ip, int port) {
        try {
            this.clientSocket = new Socket(ip, port);
            this.output = new PrintWriter(clientSocket.getOutputStream(), true);
            this.input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.println(input.readLine());
        } catch (Exception e) {
            System.out.println(e + " Error trying to start connection by client");

        }
    }

    public void connectionLoop() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String outputLine = scanner.nextLine();
                if (outputLine.equals("end")) {
                    break;
                }

                System.out.println(sendMessageGetResponse(outputLine));
            }
        } catch (Exception e) {
            System.out.println(e + " Error in connection loop");
        }

        System.out.println("Connection Loop Terminated");
    }

    public String sendMessageGetResponse(String msg) {
        try {
            this.output.println(msg);
            String response = this.input.readLine();
            return response;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Error trying to send and get a message");
            return "ERROR";
        }
    }

    public void stopConnection() {
        try {
            this.output.close();
            this.input.close();
            this.clientSocket.close();

        } catch (Exception e) {
            System.out.println(e + " Error trying to stop connection by client");
        }
    }

    public PrintWriter getOutput() {
        return this.output;
    }

    public BufferedReader getInput() {
        return this.input;
    }
}
