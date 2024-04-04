package backupSystem.util;

public interface FileProcessorInterface {
    public String read();
    public void write(String messages);
    public void close();
}
