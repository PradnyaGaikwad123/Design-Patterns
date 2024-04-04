package backupSystem.util;

// FilterPrimeImpl should return true only if the value is a prime number
public class FilterPrimeImpl implements FilterI{
    @Override
    public boolean check(int value) {
        return CheckPrime.isPrime(value);
    }
}
