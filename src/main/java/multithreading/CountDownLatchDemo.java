package multithreading;

import java.util.concurrent.CountDownLatch;

/**
 * run several threads at the same time
 */
public class CountDownLatchDemo {
    private static final Integer THREAD_QUANTITY = 5;
    private static final CountDownLatch THREAD_STARTER = new CountDownLatch(THREAD_QUANTITY + 1);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_QUANTITY; i++) {
            new SimpleThread("thread " + i).start();
        }
        THREAD_STARTER.countDown(); // the latest action to start all threads
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf(getName() + " starts at: " + System.currentTimeMillis() + "\n");
        }
    }
}
