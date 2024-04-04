package primeService.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import primeService.util.MyLogger;

public class ClientMenu {
    private BufferedReader reader;
    private PrintWriter writer;
    public BufferedReader serverReader;
    private String clientName;
    public MyLogger logger;

    public ClientMenu(BufferedReader reader, PrintWriter writer, BufferedReader serverReader, MyLogger loggerIn) {
        this.reader = reader;
        this.writer = writer;
        this.serverReader = serverReader;
        logger = loggerIn;
    }

    public void displayMenu() {
        logger.writeMessage("\n-------------- Client Menu --------------", MyLogger.DebugLevel.CLIENT_SOCKET);
        logger.writeMessage("[1] Set client name", MyLogger.DebugLevel.CLIENT_SOCKET);
        logger.writeMessage("[2] Enter number to query for prime", MyLogger.DebugLevel.CLIENT_SOCKET);
        logger.writeMessage("[3] What is the server response?", MyLogger.DebugLevel.CLIENT_SOCKET);
        logger.writeMessage("[4] Quit", MyLogger.DebugLevel.CLIENT_SOCKET);
        logger.writeMessage("Enter your choice: ", MyLogger.DebugLevel.CLIENT_SOCKET);
    }

    public void setClientName() throws IOException {
        logger.writeMessage("Enter client name: ", MyLogger.DebugLevel.CLIENT_SOCKET);
        clientName = reader.readLine();
    }

    public void queryNumberForPrime() throws IOException {
        int queryNumber = 0;
        try {
            logger.writeMessage("Enter number to query for prime: ", MyLogger.DebugLevel.CLIENT_SOCKET);
            queryNumber = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            logger.writeMessage("Invalid input. Please enter a valid integer for the prime query.", MyLogger.DebugLevel.CLIENT_SOCKET);
        } finally{}
            writer.println("<primeQuery><clientName>" + clientName + "</clientName><isPrime>" + queryNumber + "</isPrime></primeQuery>");  
    }

    public void printServerResponse(String response) throws IOException {
        logger.writeMessage("Server response: " + response, MyLogger.DebugLevel.CLIENT_SOCKET);
    }

    public void displayInvalidChoice() {
        logger.writeMessage("Invalid choice. Please try again.", MyLogger.DebugLevel.CLIENT_SOCKET);
    }

    public String getClientName() {
        return clientName;
    }

    public BufferedReader getReader() {
        return reader;
    }
}
