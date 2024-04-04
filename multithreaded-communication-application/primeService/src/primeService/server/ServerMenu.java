package primeService.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import primeService.socket.PrimeServerSocket;
import primeService.util.MyLogger;

public class ServerMenu {
    private AllPrimeQueries allPrimeQueries;
    private PrimeServerSocket primeServerSocket;
    public Scanner scanner;
    public MyLogger logger;
    public ServerSocket serverSocket;
    int choice = 0;

    public ServerMenu(AllPrimeQueries allPrimeQueries, PrimeServerSocket primeServerSocketIn, MyLogger loggerIn, ServerSocket serverSocketIn) {
        this.allPrimeQueries = allPrimeQueries;
        this.scanner = new Scanner(System.in);
        primeServerSocket = primeServerSocketIn;
        serverSocket = serverSocketIn;
        logger = loggerIn;
    }

    public void displayMenu() {
        logger.writeMessage("\n------------ Server Menu -----------", MyLogger.DebugLevel.SERVER_SOCKET);
        logger.writeMessage("[1] Client Name [print the name and query integer]", MyLogger.DebugLevel.SERVER_SOCKET);
        logger.writeMessage("[2] All Client Queries [print all names and queries so far]", MyLogger.DebugLevel.SERVER_SOCKET);
        logger.writeMessage("[3] Quit [quit the server]", MyLogger.DebugLevel.SERVER_SOCKET);
    }

    public void processMenuChoice() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                displayMenu();
                logger.writeMessage("Enter your choice: ", MyLogger.DebugLevel.SERVER_SOCKET);

                int choice = 0;
                try {
                    choice = Integer.parseInt(reader.readLine());
                } catch (NumberFormatException ex) {
                    logger.writeMessage("Exception caught: "+ ex.getMessage(), MyLogger.DebugLevel.SERVER_SOCKET);
                    logger.writeMessage("Invalid input. Please enter a valid integer choice.", MyLogger.DebugLevel.SERVER_SOCKET);
                    continue;
                } finally{}

                switch (choice) {
                    case 1:
                        logger.writeMessage("Enter client name: ", MyLogger.DebugLevel.SERVER_SOCKET);
                        String clientName = reader.readLine();
                        printClientNameAndQuery(clientName);
                        break;
                    case 2:
                        printAllClientQueries();
                        break;
                    case 3:
                        logger.writeMessage("Quitting the server.", MyLogger.DebugLevel.SERVER_SOCKET);
                        primeServerSocket.notifyClientsToExit();
                        serverSocket.close();
                        System.exit(0);
                        break;
                    default:
                        logger.writeMessage("Invalid choice. Please try again.", MyLogger.DebugLevel.SERVER_SOCKET);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{}
    }

    private void printClientNameAndQuery(String clientName) {
        if (allPrimeQueries.getQueries().containsKey(clientName)) {
            List<Integer> queries = allPrimeQueries.getQueries().get(clientName);
            logger.writeMessageWithoutNewline(clientName + " ", MyLogger.DebugLevel.SERVER_SOCKET);
            for (int query : queries) {
                logger.writeMessageWithoutNewline(query + " ", MyLogger.DebugLevel.SERVER_SOCKET);
            }
            logger.writeMessage(" ", MyLogger.DebugLevel.SERVER_SOCKET);
        } else {
            logger.writeMessage("Client not found", MyLogger.DebugLevel.SERVER_SOCKET);
        }
    }

    private void printAllClientQueries() {
        logger.writeMessage("\nAll Client Queries: ", MyLogger.DebugLevel.SERVER_SOCKET);
        for (Map.Entry<String, List<Integer>> entry : allPrimeQueries.getQueries().entrySet()) {
            String clientName = entry.getKey();
            List<Integer> queries = entry.getValue();
            logger.writeMessageWithoutNewline(clientName + " ", MyLogger.DebugLevel.SERVER_SOCKET);
            for (int query : queries) {
                logger.writeMessageWithoutNewline(query + " ", MyLogger.DebugLevel.SERVER_SOCKET);
            }
            logger.writeMessage(" ", MyLogger.DebugLevel.SERVER_SOCKET);
        }
    }
}
