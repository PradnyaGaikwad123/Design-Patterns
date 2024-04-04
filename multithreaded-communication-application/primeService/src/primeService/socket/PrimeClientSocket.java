package primeService.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import primeService.util.MyLogger;

public class PrimeClientSocket extends Thread {
    private String host;
    private int port;
    public int debug;
    public String clientName;
    public MyLogger logger;

    public PrimeClientSocket(String host, int port, int debug, MyLogger loggerIn) {
        this.host = host;
        this.port = port;
        this.debug = debug;
        logger = loggerIn;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(host, port);
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            while (true) {
                PrimeClientWorker clientWorker = new PrimeClientWorker(reader, writer, serverReader, logger);
                clientWorker.work();
            }

        } catch (IOException e) {
            System.err.println("Exception caught: " +e.getMessage());
            e.printStackTrace();
        } finally{}
    }
}
