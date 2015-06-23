package lesson7;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

/**
 * Created by Sergius on 12.06.2015.
 */
public class MyThreadPool implements Executor {

    private final BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(256);

    private final Thread[] pool;

    public MyThreadPool(int threadCount) {

        this.pool = new Thread[threadCount];
        for (int k = 0; k < threadCount; k++) {
            pool[k] = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Runnable nextTask = taskQueue.take();
                            nextTask.run(); //Âûçîâ taskè
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                }
            });
            pool[k].start();
        }
    }

    @Override
    public void execute(Runnable command) {
        if (!taskQueue.offer(command)) {
            System.out.println("Rejected!");
        }

//        try {
//            taskQueue.put(command);
//        } catch (InterruptedException e) {
//            throw new Error("NEVER!", e);
//        }
    }
}
