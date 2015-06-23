package lesson5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

// Producers/Consumers

/**
 * j.u.c
 * - queue
 * - collection
 * - synchronizers
 * - atomics
 */
public class App00 {
//    final static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(16);
    final static BlockingQueue<Integer> queue = new LinkedBlockingDeque<>();

    public static void main(String[] args) {
        //Producer
        new Thread(new Runnable() {
            @Override
            public void run() {
                int counter = 0;
                while (true) {
                    try {
                        Thread.sleep(1000);
                        queue.put(++counter);
                        System.out.println("put: " + counter);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        //Consumer
        new Thread(new Runnable() {
            @Override
            public void run() {
                //while (true) {
                for (int i = 0; i < 3; i++) {
                    try {
                        System.out.println("... wait for take");
                        int data = queue.take(); //block thread
//                        int data = queue.poll(); null
//                        int data = queue.remove(); NoSuchException
//                        Integer data = queue.poll(1, TimeUnit.SECONDS);
//                        if (data == null) {
//                            System.out.println("NO data");
//                        }
                        System.out.println("take: " + data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
