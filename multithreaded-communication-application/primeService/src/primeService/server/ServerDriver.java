package primeService.server;

import primeService.socket.PrimeServerSocket;
import primeService.util.MyLogger;

public class ServerDriver {
   
    public static void startServer(int port, int debug, MyLogger logger) {
        logger.writeMessage("Started server.....", MyLogger.DebugLevel.SERVER_SOCKET);

        AllPrimeQueries allPrimeQueries = new AllPrimeQueries();
        
        // Start the server
        PrimeServerSocket serverSocket = new PrimeServerSocket(port, allPrimeQueries, logger);
        serverSocket.start();
    }
}
