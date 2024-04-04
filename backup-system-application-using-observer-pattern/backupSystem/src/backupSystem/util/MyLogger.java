package backupSystem.util;

public class MyLogger {

    public static enum DebugLevel {
        NONE, DRIVER, TREE_BUILDER, FILE_PROCESSOR, FILE_WRITER
    };

    private static DebugLevel debugLevel;
    /**
     * Setting debug level for the value received.
     * @param levelIn
     */
    public static void setDebugValue(int levelIn) {
        switch (levelIn) {
            case 4:
                debugLevel = DebugLevel.FILE_WRITER;
                break;
            case 3:
                debugLevel = DebugLevel.FILE_PROCESSOR;
                break;
            case 2:
                debugLevel = DebugLevel.TREE_BUILDER;
                break;
            case 1:
                debugLevel = DebugLevel.DRIVER;
                break;
            default:
                debugLevel = DebugLevel.NONE;
                break;
        }
    }

    public static void setDebugValue(DebugLevel levelIn) {
        debugLevel = levelIn;
    }

    public static void writeMessage(String messageIn, DebugLevel levelIn) {
        if (levelIn == debugLevel)
            System.out.println(messageIn);
    }

    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }
}