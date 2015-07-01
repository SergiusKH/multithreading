package lesson14;

import org.multiverse.api.StmUtils;
import org.multiverse.api.references.TxnInteger;

/**
 * Created by Sergius on 29.06.2015.
 */
public class AccountTx {
    private final TxnInteger balance; // AtomicInteger

    public AccountTx(int balance) {

        this.balance = StmUtils.newTxnInteger(balance);
    }

    public void incBalance(final int amount) {
        balance.increment(amount);
        if (balance.get() < 0) {
            StmUtils.abort();
        }
    }

    public int getBalance() {
        return balance.get();
    }

    @Override
    public String toString() {
        return "Acc{" + balance.get() + "}";
    }
}
