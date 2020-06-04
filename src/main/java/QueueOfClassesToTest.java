import java.util.LinkedList;

public class QueueOfClassesToTest {
    private final int nThreads;
    private final ClassTester[] threads;
    private final LinkedList queue;

    public QueueOfClassesToTest(int nThreads) {
        this.nThreads = nThreads;
        queue = new LinkedList();
        threads = new ClassTester[nThreads];

        for (int i = 0; i < nThreads; i++) {
            threads[i] = new ClassTester();
            threads[i].start();
        }
    }

    public void execute(Runnable r) {
        synchronized (queue) {
            queue.addLast(r);
            queue.notify();
        }
    }

    private class ClassTester extends Thread {
        public void run() {
            Runnable runnable;

            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException ignored) {
                        }
                    }
                    runnable = (Runnable) queue.removeFirst();
                }
                try {
                    runnable.run();
                } catch (RuntimeException e) {
                    System.out.println("Something bad happened in the ClassTester thread");
                }
            }
        }
    }
}
