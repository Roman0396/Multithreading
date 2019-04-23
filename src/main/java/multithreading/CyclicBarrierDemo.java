package multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * run several threads at the same time
 */
public class CyclicBarrierDemo {

    private static final Integer THREAD_QUANTITY = 9;

    private static final Integer THREADS_TO_RUN_AT_THE_SAME_TIME = 3;

    private static final CyclicBarrier BARRIER = new CyclicBarrier(THREADS_TO_RUN_AT_THE_SAME_TIME);
    private static final List<Thread> threads = new ArrayList<Thread>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < THREAD_QUANTITY; i++) {
            Thread currentThread = new SimpleThread("thread " + i);
            threads.add(currentThread);
            currentThread.start();
            Thread.sleep(1000);//interval between runs
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
            try {
                BARRIER.await();
                System.out.println(getName() + " starts at: " + System.currentTimeMillis());
                Thread.sleep(5000); // execution
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}