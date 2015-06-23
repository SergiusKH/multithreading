package lesson7;

import java.util.concurrent.Executor;

/**
 * Created by Sergius on 12.06.2015.
 */
public class Demo01 {

    public static void main(String[] args) {

        Executor executor0 = getExecuter();
        executor0.execute(getTask());
        executor0.execute(getTask());

        Executor executor1 = getExecuter();
        executor1.execute(getTask());
        executor1.execute(getTask());
        executor1.execute(getTask());
    }

    private static Runnable getTask() {
        throw new UnsupportedOperationException();
    }

    private static Executor getExecuter() {
        throw new UnsupportedOperationException();
    }
}
