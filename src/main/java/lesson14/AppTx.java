package lesson14;

import org.multiverse.api.StmUtils;
import org.multiverse.api.Txn;
import org.multiverse.api.callables.TxnCallable;
import org.multiverse.api.callables.TxnIntCallable;
import org.multiverse.api.exceptions.DeadTxnException;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Sergius on 30.06.2015.
 */
public class AppTx {
    static Random rnd = new Random();
    static ExecutorService exec = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        final AccountTx[] accounts = new AccountTx[] {
                new AccountTx(100), new AccountTx(0),
                new AccountTx(100), new AccountTx(0),
                new AccountTx(100), new AccountTx(0),
                new AccountTx(100), new AccountTx(0),
                new AccountTx(100), new AccountTx(0)
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

    private static String toStr(final AccountTx[] accounts) throws Exception{
        return StmUtils.atomic(new TxnCallable<String>() {
            @Override
            public String call(Txn txn) throws Exception {
                return Arrays.toString(accounts);
            }
        });
    }

    private static int sum(final AccountTx[] accounts) throws Exception{
        return StmUtils.atomic(new TxnIntCallable() {
            @Override
            public int call(Txn txn) throws Exception {
                int result = 0;
                for (AccountTx account : accounts) {
                    result += account.getBalance();
                }
                return result;
            }
        });
    }

    private static boolean transfer(final AccountTx from, final AccountTx to, final int amount) {
        try {
            StmUtils.atomic(new Runnable() {
                @Override
                public void run() {
                    from.incBalance(-amount);
                    to.incBalance(+amount);
                }
            });
            return true;
        } catch (DeadTxnException e) {
            return false;
        }
    }
}
