package lesson14;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Sergius on 29.06.2015.
 */
public class AccountLock {
    private static final AtomicInteger idGenerator = new AtomicInteger(0);
    public final int id = idGenerator.getAndIncrement();
    public final Lock lock = new ReentrantLock();
    private int balance;

    public AccountLock(int balance) {
        this.balance = balance;
    }

    public boolean incBalance(int amount) {
        if (balance + amount >=0) {
            balance += amount;
            return true;
        } else {
            return false;
        }
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Acc{" + balance + "}";
    }
}
