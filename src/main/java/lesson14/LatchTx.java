package lesson14;

import org.multiverse.api.StmUtils;
import org.multiverse.api.Txn;
import org.multiverse.api.callables.TxnBooleanCallable;
import org.multiverse.api.references.TxnBoolean;

/**
 * Created by Sergius on 30.06.2015.
 */
public class LatchTx {

    private TxnBoolean open = StmUtils.newTxnBoolean(false);

    public boolean isOpen() {
        return StmUtils.atomic(new TxnBooleanCallable() {
            @Override
            public boolean call(Txn txn) throws Exception {
                return open.get();
            }
        });
    }

    public void doOpen() {
        StmUtils.atomic(new Runnable() {
            @Override
            public void run() {
                open.set(true);
            }
        });
    }

    public void await() {
        StmUtils.atomic(new Runnable() {
            @Override
            public void run() {
                if (!open.get()) {
                    StmUtils.retry();
                }
            }
        });
    }
}
