package lesson14;

import org.multiverse.api.StmUtils;

/**
 * Created by Sergius on 01.07.2015.
 */
public class App {

    public static void main(String[] args) {
        final MyTxStack<String> stack0 = new MyTxStack<>();
        final MyTxStack<String> stack1 = new MyTxStack<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    StmUtils.atomic(new Runnable() {
                        @Override
                        public void run() {
                            if (Math.random() > 0.5) {
                                stack0.push("A0");
                            } else {
                                stack1.push("B1");
                            }
                        }
                    });
                }
            }
        }).start();

        while (true) {
            StmUtils.atomic(new Runnable() {
                @Override
                public void run() {
//                    if (!stack0.isEmpty() && !stack1.isEmpty()) {
//                        System.out.println(stack0.pop() + ":" + stack1.pop());
//                    } else {
//                        StmUtils.retry();
//                    }
                    if (!stack0.isEmpty()) {
                        System.out.println(stack0.pop());
                    } else if (!stack1.isEmpty()) {
                        System.out.println(stack1.pop());
                    } else {
                        StmUtils.retry();
                    }
                }
            });
        }
    }
}
