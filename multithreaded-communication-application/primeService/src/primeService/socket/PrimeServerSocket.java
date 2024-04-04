package primeService.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import primeService.server.AllPrimeQueries;
import primeService.server.ServerMenuThread;
import primeService.util.MyLogger;

public class PrimeServerSocket extends Thread {
    private int port;
    private AllPrimeQueries allPrimeQueries;
    private List<Socket> clientSockets;
    public MyLogger logger;

    public PrimeServerSocket(int port, AllPrimeQueries allPrimeQueries, MyLogger loggerIn) {
        this.port = port;
        this.allPrimeQueries = allPrimeQueries;
        this.clientSockets = new ArrayList<>();
        this.logger = loggerIn;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.writeMessage("Waiting for clients to connect...", MyLogger.DebugLevel.SERVER_SOCKET);
            
            ServerMenuThread serverMenuThread = new ServerMenuThread(allPrimeQueries, this, logger, serverSocket);
            serverMenuThread.start();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientSockets.add(clientSocket);
                PrimeServerWorker serverWorker = new PrimeServerWorker(clientSocket, allPrimeQueries, this, logger, serverSocket);                
                new Thread(serverWorker).start();
            }
        } catch (IOException e) {
            System.err.println("Exception caught: " +e.getMessage());
            e.printStackTrace();
        } finally {}
    }

    public void notifyClientsToExit() {
        for (Socket clientSocket : clientSockets) {
            try {
                clientSocket.getOutputStream().write("EXIT".getBytes());
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Exception caught: " +e.getMessage());
                e.printStackTrace();
            } finally{}
        }
    }

    public List<Socket> getClientSockets() {
        return this.clientSockets;
    }
}
