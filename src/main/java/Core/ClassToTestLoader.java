package Core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ClassToTestLoader {
    public static void loadClassesToQueue(int nThreads, List<String> args) {
        System.out.println("Loading classes started");
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
        System.out.println("Loading classes finished");
        System.out.println("------------------------------------------------------------");

        ExecutorService executor = Executors.newFixedThreadPool(nThreads);

        for (ClassToTest cl : classList) {
            executor.execute(() -> {
                int amountOfTests = 0;
                int successfullTests = 0;
                List<String> brokenTestsList = new ArrayList<>();
                ReentrantLock lock = new ReentrantLock();
                try {
                    lock.lock();
                    System.out.println("locked by thread " + Thread.currentThread().getName());
                    ClassToTest targetClass = cl.getClass().newInstance();
                    for (Method m: cl.getClass().getMethods()) {
                        if (m.isAnnotationPresent(Before.class)) {
                            targetClass.updateBeforeMethod(m);
                        } else if (m.isAnnotationPresent(After.class)) {
                            targetClass.updateAfterMethod(m);
                        }
                    }
                    for (Method m: cl.getClass().getMethods()) {
                        if (m.isAnnotationPresent(Test.class)) {
                            amountOfTests++;
                            if (targetClass.runOneTest(m) == 1) {
                                successfullTests++;
                            } else {
                                brokenTestsList.add(m.getName());
                            }
                        }
                    }
                } catch (Throwable err) {
                    err.printStackTrace();
                }
                finally {
                    lock.unlock();
                    System.out.println("unlocked by thread " + Thread.currentThread().getName());
                    System.out.println(cl.getClass().getName() + " REPORT: SUCCESSFULL " + successfullTests + " OUT OF " + amountOfTests);
                    if (brokenTestsList.size() > 0) {
                        System.out.println("BROKEN TESTS: ");
                        for (String str: brokenTestsList) {
                            System.out.println(str);
                        }
                        System.out.println("-------------------------------------------");
                    }
                }
            });
        }
    }
}
