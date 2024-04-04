package primeService.driver;

import primeService.client.ClientDriver;
import primeService.server.ServerDriver;
import primeService.util.MyLogger;

public class PrimeDriver {
    public static void main(String[] args) {

        if ((args.length != 2 && args.length != 3) || args[0].equals("${arg0}") || args[1].equals("${arg1}")) {
            System.err.println("Error: Incorrect number of arguments. Program accepts 2 or 3 arguments.");
            System.exit(0);
        }
       
        int port= 0;
        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
                System.err.println("Exception caught: Invalid PORT NUMBER");
                e.printStackTrace();
                System.exit(0); 
        }finally{}
        
        String host = (args.length == 3) ? args[1] : null;
        
        MyLogger logger = MyLogger.getInstance();

        int debug = 0;
        if (args[2].equals("${arg2}")) {
            try {
                debug = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Exception caught: Invalid DEBUG_VALUE");
                e.printStackTrace();
                System.exit(0); 
            }finally{}
            logger.setDebugValue(debug);
            ServerDriver.startServer(port, debug, logger);
        } else {
            try {
                debug = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.err.println("Exception caught: Invalid DEBUG_VALUE");
                e.printStackTrace();
                System.exit(0); 
            }finally{}
            logger.setDebugValue(debug);
            ClientDriver.startClient(host, port, debug, logger);
        }
    }        
}
