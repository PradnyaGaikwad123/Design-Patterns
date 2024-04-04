package backupSystem.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node implements SubjectI, ObserverI{
    int bNumber;
    String firstName;
    Node left, right;
    private Map<FilterI, List<ObserverI>> filterObserverMap = new HashMap<>();

    public Node() {

    }

    public Node(int bNumber, String firstName) {
        this.bNumber = bNumber;
        this.firstName = firstName;
        left = right = null;
    }

    /**
     * Registers an observer to receive updates and associates it with a filter.
     *
     * @param observer The observer to register for updates.
     * @param filter   The filter that determines whether the observer should receive updates.
     */
    @Override
    public void registerObserver(ObserverI observer, FilterI filter) {;
        List<ObserverI> observers = filterObserverMap.get(filter);
        if (observers == null) {
            observers = new ArrayList<>();
            filterObserverMap.put(filter, observers);
        }
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(ObserverI observer, FilterI filter) {
        List<ObserverI> observers = filterObserverMap.get(filter);
        if (observer != null) {
            if (observers.contains(observer)) {
            observers.remove(observer);
            }
        }
    }

    @Override
    public void notifyAllObservers(int updateValue) {
        for (Map.Entry<FilterI, List<ObserverI>> entry : filterObserverMap.entrySet()) {
            FilterI filter = entry.getKey();
            List<ObserverI> observers = entry.getValue();

            if (filter.check(bNumber)) {
                for (ObserverI observer : observers) {
                    observer.update(updateValue);
                }
            }
        }
    }

    public Map<FilterI, List<ObserverI>> getFilterObserverMap() {
        return filterObserverMap;
    }

    @Override
    public void update(int updateValue) {
        // Update the bNumber value of the current node
        bNumber += updateValue;
    }

    @Override
    public String toString() {
        return bNumber + ":" + firstName;
    }
}