package backupSystem.util;

public interface SubjectI {
    public void registerObserver(ObserverI observer, FilterI filter);
    public void unregisterObserver(ObserverI observer, FilterI filter);
    public void notifyAllObservers(int updateValue);
}
