package backupSystem.util;

// The FilterAllImpl should always return true when the "check" method is called
public class FilterAllImpl implements FilterI{
    @Override
    public boolean check(int value) {
        return true;
    }
}
