package primeService.socket;

import primeService.server.AllPrimeQueries;
import primeService.server.ServerMenu;
import primeService.util.CheckPrime;
import primeService.util.MyLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PrimeServerWorker implements Runnable {
    private Socket clientSocket;
    private static final int THRESHOLD = 3;
    private AllPrimeQueries allPrimeQueries;
    public ServerMenu serverMenu;
    public PrimeServerSocket primeServerSocket;
    public MyLogger logger;
    public ServerSocket serverSocket;

    public PrimeServerWorker(Socket clientSocket, AllPrimeQueries allPrimeQueries, PrimeServerSocket primeServerSocketIn, MyLogger loggerIn, ServerSocket serverSocketIn) {
        this.clientSocket = clientSocket;
        this.clientSocket = clientSocket;
        this.allPrimeQueries = allPrimeQueries;
        this.primeServerSocket = primeServerSocketIn;
        logger = loggerIn;
        serverSocket = serverSocketIn;
        this.serverMenu = new ServerMenu(allPrimeQueries, primeServerSocketIn, logger, serverSocket);
    }

    @Override
    public void run() {
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

            while(true) {
                String input = reader.readLine();
                processClientInput(input, writer);
            }
        } catch (IOException e) {
            System.err.println("Exception caught: " +e.getMessage());
            e.printStackTrace();
        } finally{}
    }

    private void processClientInput(String input, PrintWriter writer) {
        String clientName = parseClientName(input);
        int queryNumber = parseQueryNumber(input);

        allPrimeQueries.addQuery(clientName, queryNumber);

        if (queryNumber > THRESHOLD) {
            String isPrimeResponse = CheckPrime.isPrime(queryNumber);

            String response = "<primeQueryResponse><intValue>" + queryNumber + "</intValue><isPrime>" + isPrimeResponse + "</isPrime></primeQueryResponse>";
            // System.out.println("Writing to client: "+ response);
            writer.println(response);
        } else {
            writer.println("<primeQueryResponse><intValue>" + queryNumber + "</intValue><isPrime>Invalid</isPrime></primeQueryResponse>");
        }
    }

    private String parseClientName(String input) {
        try{
            int start = input.indexOf("<clientName>") + "<clientName>".length();
            int end = input.indexOf("</clientName>");
            return input.substring(start, end);
        } catch (Exception e) {
            System.err.println("Exception caught: Client input null, maybe client is closed. " +e.getMessage());
            return "";
        } finally{}
    }
    
    private int parseQueryNumber(String input) {
        int start = input.indexOf("<isPrime>") + "<isPrime>".length();
        int end = input.indexOf("</isPrime>");
        return Integer.parseInt(input.substring(start, end));
    }
}
