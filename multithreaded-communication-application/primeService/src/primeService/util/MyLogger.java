package primeService.util;

public class MyLogger {

    public enum DebugLevel {
        NONE, DRIVER, CLIENT_SOCKET, SERVER_SOCKET
    };

    private DebugLevel debugLevel;
    private static MyLogger instance;

    private MyLogger() {
        debugLevel = DebugLevel.NONE; // Default debug level
    }

    public static MyLogger getInstance() {
        if (instance == null) {
            instance = new MyLogger();
        }
        return instance;
    }

    /**
     * Setting debug level for the value received.
     * @param levelIn
     */
    public void setDebugValue(int levelIn) {
        switch (levelIn) {
            case 3:
                debugLevel = DebugLevel.DRIVER;
                break;
            case 2:
                debugLevel = DebugLevel.CLIENT_SOCKET;
                break;
            case 1:
                debugLevel = DebugLevel.SERVER_SOCKET;
                break;
            default:
                debugLevel = DebugLevel.NONE;
                break;
        }
    }

    public void setDebugValue(DebugLevel levelIn) {
        debugLevel = levelIn;
    }

    public void writeMessage(String messageIn, DebugLevel levelIn) {
        if (levelIn == debugLevel)
            System.out.println(messageIn);
    }

    public void writeMessageWithoutNewline(String messageIn, DebugLevel levelIn) {
        if (levelIn == debugLevel)
            System.out.print(messageIn);
    }

    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }

    public DebugLevel getDebugLevel() {
        return debugLevel;
    }
}
