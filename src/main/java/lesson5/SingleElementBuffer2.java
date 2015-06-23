package lesson5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Sergius on 07.06.2015.
 *
 */

// 1. fairness
// 2. lock/unlock - независимы
public class SingleElementBuffer2 {

    private final Lock lock = new ReentrantLock(true); //fairness - чесное
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();
    private Integer elem = null;

    public void put(int newElem) throws InterruptedException {
        lock.lock();
        try {
            while (this.elem != null) {
                notFull.await();
            }
            this.elem = newElem;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public boolean tryPut(int newElem) throws InterruptedException {
        if (lock.tryLock()) {
            try {
                while (this.elem != null) {
                    notFull.await();
                }
                this.elem = newElem;
                notEmpty.signal();
            } finally {
                lock.unlock();
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean tryPut(int newElem, long timeout, TimeUnit unit) throws InterruptedException {
        if (lock.tryLock(timeout, unit)) {
            try {
                while (this.elem != null) {
                    notFull.await();
                }
                this.elem = newElem;
                notEmpty.signal();
            } finally {
                lock.unlock();
                return true;
            }
        } else {
            return false;
        }
    }

    public int get() throws InterruptedException {
        lock.lock();
        int newElem = 0;
        try {
            while (this.elem == null) {
                notEmpty.await();
            }
            newElem = this.elem;
            this.elem = null;
            notFull.signal();
        } finally {
            lock.unlock();
        }
        return newElem;
    }
}
