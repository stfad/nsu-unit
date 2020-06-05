package Core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

public class ClassToTest {
    int runOneTest(Method test) {
        AtomicInteger success = new AtomicInteger(0);
        try {
            if (beforeMethod != null) {
                beforeMethod.invoke(this);
            }
            System.out.println(test.getName() + " STARTED");
            test.invoke(this);
            System.out.println(test.getName() + " PASSED");
            success.incrementAndGet();
        } catch (InvocationTargetException err) {
            Class<? extends Throwable> expectedException;
            Test annotation = test.getAnnotation(Test.class);
            if (annotation == null || annotation.expected() == Test.EmptyException.class) {
                expectedException = null;
            } else {
                expectedException = annotation.expected();
            }

            if (expectedException != null && expectedException.isAssignableFrom(err.getCause().getClass())) {
                success.incrementAndGet();
                System.out.println(test.getName() + " PASSED");
            } else {
                System.out.println("THERE WAS A FAILURE: " + err.getCause().toString());
            }
        } catch (Throwable err) {
            System.out.println(test.getName() + " FAILED BY " + err.toString());
            err.printStackTrace();
        } finally {
            try {
                if (afterMethod != null) {
                    afterMethod.invoke(this);
                }
            } catch (Throwable err) {
                err.printStackTrace();
            }
        }
        return success.intValue();
    }

    void updateBeforeMethod(Method m) throws BeforeMethodAlreadyExistsException {
        if (beforeMethod != null) {
            throw new BeforeMethodAlreadyExistsException("@Before annotation should be used only once!");
        }
        beforeMethod = m;
    }

    void updateAfterMethod(Method m) throws AfterMethodAlreadyExistsException {
        if (afterMethod != null) {
            throw new AfterMethodAlreadyExistsException("@After annotation should be used only once!");
        }
        afterMethod = m;
    }

    private Method beforeMethod, afterMethod;
}
