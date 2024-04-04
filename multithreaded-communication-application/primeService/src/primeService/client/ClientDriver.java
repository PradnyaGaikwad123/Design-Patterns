package primeService.client;

import primeService.socket.PrimeClientSocket;
import primeService.util.MyLogger;

public class ClientDriver {

    public static void startClient(String host, int port, int debug, MyLogger logger) {
        logger.writeMessage("Started client .....", MyLogger.DebugLevel.CLIENT_SOCKET);

        // Start the client
        PrimeClientSocket clientSocket = new PrimeClientSocket(host, port, debug, logger);
        clientSocket.start();
    }
}
