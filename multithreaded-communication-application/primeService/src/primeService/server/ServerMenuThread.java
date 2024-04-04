package primeService.server;

import java.net.ServerSocket;

import primeService.socket.PrimeServerSocket;
import primeService.util.MyLogger;

public class ServerMenuThread extends Thread {
        private AllPrimeQueries allPrimeQueries;
        private PrimeServerSocket pServerSocket;
        public MyLogger logger;
        public ServerSocket serverSocket;

        public ServerMenuThread(AllPrimeQueries allPrimeQueries, PrimeServerSocket pServerSocketIn, MyLogger loggerIn, ServerSocket serverSocketIn) {
            this.allPrimeQueries = allPrimeQueries;
            pServerSocket = pServerSocketIn;
            logger = loggerIn;
            serverSocket = serverSocketIn;
        }

        @Override
        public void run() {
            ServerMenu serverMenu = new ServerMenu(allPrimeQueries, pServerSocket, logger, serverSocket);
            serverMenu.processMenuChoice();
        }
}
