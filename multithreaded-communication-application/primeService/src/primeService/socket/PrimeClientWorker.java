package primeService.socket;

import primeService.client.ClientMenu;
import primeService.util.MyLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class PrimeClientWorker {
    private ClientMenu clientMenu;
    private BufferedReader serverReader;
    public PrintWriter writer;
    public BufferedReader reader;
    public MyLogger logger;
    public String response = "";

    public PrimeClientWorker(BufferedReader reader, PrintWriter writer, BufferedReader serverReaderIn, MyLogger loggerIn) {
        serverReader = serverReaderIn;
        logger = loggerIn;
        this.clientMenu = new ClientMenu(reader, writer, serverReader, logger);  
        this.writer = writer;
        this.reader = reader;
    }

    public void work() {
        try {
            while (true) {
                clientMenu.displayMenu();
                int choice = 0;
                try {
                    choice = Integer.parseInt(clientMenu.getReader().readLine());
                } catch (NumberFormatException ex) {
                    logger.writeMessage("Exception caught: "+ ex.getMessage(), MyLogger.DebugLevel.CLIENT_SOCKET);
                    logger.writeMessage("Invalid input. Please enter a valid integer choice.", MyLogger.DebugLevel.CLIENT_SOCKET);
                    continue;
                } finally{}

                switch (choice) {
                    case 1:
                        clientMenu.setClientName();
                        break;
                    case 2:
                        clientMenu.queryNumberForPrime();
                        try {
                            response = serverReader.readLine();
                            setResponse(response);
                            if (response.equals("EXIT")) {
                                throw new IOException("Server connection closed");
                            }
                        } catch (IOException e) {
                            throw new IOException("Server connection closed");
                        } finally{}
                        break;
                    case 3:
                         clientMenu.printServerResponse(response);
                        break;
                    case 4:
                        logger.writeMessage("Quitting the client.", MyLogger.DebugLevel.CLIENT_SOCKET);
                        System.exit(0);
                        break;
                    default:
                        clientMenu.displayInvalidChoice();
                }
            }

        } catch (IOException e) {
            System.err.println("Exception caught: "+ e.getMessage());
            e.printStackTrace();
            System.err.println("Server has quit, quitting client...");
            System.exit(0);
        } finally{}
    }

    public void setResponse(String resIn) {
        response = resIn;
    }
}
