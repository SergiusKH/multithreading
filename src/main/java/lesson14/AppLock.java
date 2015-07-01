package lesson14;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Sergius on 29.06.2015.
 *
 * Захватвыть блокеровки с лева на право (id объетов и сортировка по id)
 * Глобальный порядок блокуровак
 * ------------->
 * O O O O O O O
 *  1|___|2
 * AccountLock fst = (from.id < to.id) ? from : to;
 * AccountLock snd = (from.id >= to.id) ? from : to;
 * fst.lock.lock();
 * try {
 *  snd.lock.lock();
 *      try {
 *          if (from.incBalance(-amount)) {
 *              if (!to.incBalance(+amount)) {
 *                  from.incBalance(+amount);
 *              }
 *          }
 *      } finally {
 *          snd.lock.unlock();
 *      }
 *  } finally {
 *      fst.lock.unlock();
 *  }
 */
public class AppLock {
    static Random rnd = new Random();
    static ExecutorService exec = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        final AccountLock[] accounts = new AccountLock[] {
                new AccountLock(100), new AccountLock(0),
                new AccountLock(100), new AccountLock(0),
                new AccountLock(100), new AccountLock(0),
                new AccountLock(100), new AccountLock(0),
                new AccountLock(100), new AccountLock(0)
        };

        for (int i = 0; i < 4 * Runtime.getRuntime().availableProcessors(); i++) {
            exec.submit(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        int from = rnd.nextInt(accounts.length);
                        int to = rnd.nextInt(accounts.length);
                        if (from != to) {
                            int delta = rnd.nextInt(50);
                            transfer(accounts[from], accounts[to], delta);
                        }
                    }
                }
            });
        }

        Thread.sleep(1000);
        System.out.println(sum(accounts));
        System.out.println(toStr(accounts));
    }

    private static String toStr(final AccountLock[] accounts) throws Exception{
        final AccountLock[] tmp = accounts.clone();
        Arrays.sort(tmp, new Comparator<AccountLock>() {
            @Override
            public int compare(AccountLock acc0, AccountLock acc1) {
                return acc0.id - acc1.id;
            }
        });

        return lockRecursively(tmp, new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Arrays.toString(tmp);
            }
        });
    }

    private static <T> T lockRecursively(AccountLock[] accounts, Callable<T> c) throws Exception{
        if (accounts.length > 0) {
            accounts[0].lock.lock();
            try {
                return lockRecursively(Arrays.copyOfRange(accounts, 1, accounts.length), c);
            } finally {
                accounts[0].lock.unlock();
            }
        } else {
            return c.call();
        }
    }

    private static int sum(final AccountLock[] accounts) throws Exception{
        final AccountLock[] tmp = accounts.clone();
        Arrays.sort(tmp, new Comparator<AccountLock>() {
            @Override
            public int compare(AccountLock acc0, AccountLock acc1) {
                return acc0.id - acc1.id;
            }
        });
        return lockRecursively(tmp, new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int result = 0;
                for (AccountLock acc : tmp) {
                    result += acc.getBalance();
                }
                return result;
            }
        });
    }

    private static void transfer(final AccountLock from, final AccountLock to, final int amount) {
        AccountLock fst = (from.id < to.id) ? from : to;
        AccountLock snd = (from.id >= to.id) ? from : to;
        fst.lock.lock();
        try {
            snd.lock.lock();
            try {
                if (from.incBalance(-amount)) {
                    if (!to.incBalance(+amount)) {
                        from.incBalance(+amount);
                    }
                }
            } finally {
                snd.lock.unlock();
            }
        } finally {
            fst.lock.unlock();
        }
    }
}
