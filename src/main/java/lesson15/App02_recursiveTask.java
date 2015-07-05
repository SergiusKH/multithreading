package lesson15;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Sergius on 05.07.2015.
 */
public class App02_recursiveTask {

    public static void main(String[] args) {
        long result = new ForkJoinPool().invoke(new Task(0, 1_000_000));
        System.out.println(result);
    }

    public static class Task extends RecursiveTask<Long> {
        private final int from;
        private final int to;

        public Task(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            if (to - from < 10_000) {
                return directCalc();
            } else {
                int mid = (from - to) >>> 1;
                Task taskLeft = new Task(from, mid);
                Task taskRight = new Task(mid, to);
                taskLeft.fork(); // start()
                taskRight.fork(); // start()
                return taskLeft.join() + taskRight.join();
            }
        }

        private long directCalc() {
            long result = 0;
            for (int index = from; index < to; index++) {
                if (index % 3 != 0 && index % 5 != 0) {
                    result += index;
                }
            }
            return result;
        }
    }
}
