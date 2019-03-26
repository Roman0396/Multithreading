package multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * run several threads at the same time
 */
public class CyclicBarrierDemo {
    private static final Integer THREAD_QUANTITY = 9;

    private static final Integer THREADS_TO_RUN_AT_THE_SAME_TIME = 3;

    private static final CyclicBarrier BARRIER = new CyclicBarrier(THREADS_TO_RUN_AT_THE_SAME_TIME, new StartTask());

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < THREAD_QUANTITY; i++) {
            new Thread(new SimpleThread("thread" + i)).start();
            Thread.sleep(100);
        }
    }

    public static class StartTask implements Runnable {

        public void run() {
            try {
                Thread.sleep(100);
                System.out.println("run threads:");
            } catch (InterruptedException e) {
            }
        }
    }

    public static class SimpleThread extends Thread {

        SimpleThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                BARRIER.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.printf(getName() + " starts at: " + System.currentTimeMillis() + "\n");
        }
    }
}
