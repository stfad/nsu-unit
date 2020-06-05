package Core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ClassToTestLoader {
    public static void loadClassesToQueue(int nThreads, List<String> args) {
        List<ClassToTest> classList = new ArrayList<>();
        for (String className : args) {
            try {
                Class<?> testClass = Class.forName(className);
                classList.add((ClassToTest) testClass.newInstance());
                System.out.println("Class " + className + " was successfully loaded");
            } catch (ClassNotFoundException err) {
                System.out.println("Class " + className + " can't be tested by " + err.toString());
            } catch (Throwable err) {
                err.printStackTrace();
            }
        }

        ExecutorService executor = Executors.newFixedThreadPool(nThreads);

        for (ClassToTest cl : classList) {
            executor.execute(() -> {
                ReentrantLock lock = new ReentrantLock();
                try {
                    lock.lock();
                    System.out.println("locked by thread " + Thread.currentThread().getName());
                    ClassToTest targetClass = cl.getClass().newInstance();
                    for (Method m: cl.getClass().getMethods()) {
                        if (m.getName().startsWith("test")) {
                            targetClass.runOneTest(m);
                        }
                    }
                } catch (Throwable err) {
                    err.printStackTrace();
                }
                finally {
                    lock.unlock();
                    System.out.println("unlocked by thread " + Thread.currentThread().getName());
                }
            });
        }
    }
}
