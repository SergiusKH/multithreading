package lesson5;

public class DeadThreadsException {

    public static void main(String[] args) {

        ThreadGroup group = new ThreadGroup("group1");

        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                group.interrupt();
            }
        };

        Thread p0 = new Thread(group, new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    System.out.println("p0 - kill");
                }
            }
        });

        p0.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        p0.start();

        Thread p1 = new Thread(group, new Runnable() {
            @Override
            public void run() {
                System.out.println("p1 - kill");
                throw new Error();
            }
        });

        p1.setUncaughtExceptionHandler(uncaughtExceptionHandler);
        p1.start();
    }
}
