package primeService.server;

import java.util.List;
import java.util.Map;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class AllPrimeQueries {
    private Map<String, List<Integer>> queries;

     /**
     * Constructs an AllPrimeQueries object initializing
     *  the underlying storage for queries.
     */
    public AllPrimeQueries() {
        this.queries = new ConcurrentHashMap<>();
    }

    /**
     * Adds a new query number associated with the given client name.
     *
     * @param clientName  The name of the client making the query.
     * @param queryNumber The number being queried for primality.
     */
    public synchronized void addQuery(String clientName, int queryNumber) {
        queries.computeIfAbsent(clientName, k -> new CopyOnWriteArrayList<>()).add(queryNumber);
    }

    /**
     * Retrieves a snapshot of all stored queries in the form 
     * of a mapping of client names
     * to their respective lists of query numbers.
     *
     * @return A ConcurrentHashMap containing client names as 
     * keys and their query lists as values.
     */
    public synchronized Map<String, List<Integer>> getQueries() {
        return new ConcurrentHashMap<>(queries);
    }
}
