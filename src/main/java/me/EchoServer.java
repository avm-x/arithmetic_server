package me;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

            output.println("Connection Stablished with Arithmetic Echo server" + "\n" + "Format of operations: a + b");

            MathOperations mathOperations = new MathOperations();

            while (true) {
                while (input.ready()) {
                    Pattern numPattern = Pattern.compile("([0-9]+)");
                    Pattern symPattern = Pattern.compile("[+|/|-|*]");

                    String inLine = input.readLine().trim();

                    String[] numsSplit = symPattern.split(inLine);
                    String[] symsSplit = numPattern.split(inLine);

                    String outLine = "";

                    if (numsSplit.length > 1 && symsSplit.length > 0 && symsSplit.length <= 2) {

                        if (symsSplit[1].equals("+")) {
                            outLine += MathOperations.sum(Integer.valueOf(numsSplit[0]), Integer.valueOf(numsSplit[1]));
                        }

                        else if (symsSplit[1].equals("-")) {
                            outLine += MathOperations.minus(Integer.valueOf(numsSplit[0]),
                                    Integer.valueOf(numsSplit[1]));

                        }

                        else if (symsSplit[1].equals("*")) {
                            outLine += MathOperations.multiply(Integer.valueOf(numsSplit[0]),
                                    Integer.valueOf(numsSplit[1]));

                        }

                        else if (symsSplit[1].equals("/")) {
                            outLine += MathOperations.divide(Integer.valueOf(numsSplit[0]),
                                    Integer.valueOf(numsSplit[1]));

                        }

                        output.println("Processed in server: " + outLine);
                    } else {
                        output.println("Wrong input: " + inLine + ". Input should be like 2 + 3");
                    }
                }

            }

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
