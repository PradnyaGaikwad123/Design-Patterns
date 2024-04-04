package backupSystem.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessor implements FileProcessorInterface{
    
    public String fileName;
    public String errorFileName;
    public BufferedReader reader;
    FileWriter writer;

    public FileProcessor() {
    }

    // for file reading
    public FileProcessor(String fileName, String errorFileName) {
        this.fileName = fileName;
        this.errorFileName = errorFileName;
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (IOException e) {
            System.err.println("Error: Error in processing input file. File Not Found Exception caught.");
            e.printStackTrace();
            writeToErrorLogFile(errorFileName, "Error: Error in processing input file. File Not Found Exception caught.");
            System.exit(0);
        } finally {
            
        }
    }

    // for file writing
    public FileProcessor(String fileName) {
        try {
            writer = new FileWriter(fileName);
        } catch (IOException e) {
            System.err.println("Error in writing to file.");
            e.printStackTrace();
            System.exit(0);
        } finally {
            
        }
    }

    public void writeToErrorLogFile(String errorFileName, String errorMessage) {
        try (FileWriter errorFileWriter = new FileWriter(errorFileName)) {
            errorFileWriter.write(errorMessage + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to the errorLog file: " + errorFileName);
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * Reads a line of text from the input source and returns it as a string.
     *
     * This method attempts to read a line of text from the input source and returns
     * it as a string. If an IOException occurs during the read operation, it is
     * caught and logged, and the method returns null. The 'finally' block can be
     * used for any necessary cleanup or additional actions.
     *
     * @return A line of text read from the input source as a string, or null if an
     *         IOException occurs.
     */
    public String read() {
        MyLogger.writeMessage("Reading Input File", MyLogger.DebugLevel.FILE_PROCESSOR);
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.err.println("Error in reading the file");
            e.printStackTrace();
            System.exit(0);
            return null;
        } finally {
            
        }
    }

    public void write(String message) {
        try {
            writer.write(message);
        } catch (IOException e) {
            System.err.println("Error in writing to file.");
            e.printStackTrace();
            System.exit(0);
        } finally {
            
        }
    }

    public void close() {
        try {
            if (reader != null) {
                reader.close();
            }
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("Error in closing file");
            e.printStackTrace();
            System.exit(0);
        } finally {
            
        }
    }

    public String toString(Object obj) {
        return obj.toString();
    }
}
