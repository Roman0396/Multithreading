package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * run several threads at the same time
 */
public class CountDownLatchDemo {
    private static final Integer THREAD_QUANTITY = 5;
    private static final CountDownLatch THREAD_STARTER = new CountDownLatch(THREAD_QUANTITY);
    private static final List<Thread> threads = new ArrayList<Thread>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < THREAD_QUANTITY; i++) {
            Thread currentThread = new SimpleThread("thread " + i);
            threads.add(currentThread);
            currentThread.start();
        }
        for (int i = 0; i < THREAD_QUANTITY; i++) {
            Thread currentThread = threads.get(i);
            currentThread.join();
        }
        System.out.println("all threads are finished");
    }

    public static class SimpleThread extends Thread {

        SimpleThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            THREAD_STARTER.countDown();
            try {
                THREAD_STARTER.await();
                System.out.println(getName() + " starts at: " + System.currentTimeMillis());
                Thread.sleep(5000); // executing
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
