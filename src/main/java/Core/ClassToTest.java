package Core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassToTest {
    int runOneTest(Method test) {
        int success = 0;
        try {
            if (beforeMethod != null) {
                beforeMethod.invoke(this);
            }
            System.out.println(test.getName() + " STARTED");
            test.invoke(this);
            System.out.println(test.getName() + " PASSED");
            success++;
        } catch (InvocationTargetException err) {
            Class<? extends Throwable> expectedException;
            Test annotation = test.getAnnotation(Test.class);
            if (annotation == null || annotation.expected() == Test.EmptyException.class) {
                expectedException = null;
            } else {
                expectedException = annotation.expected();
            }

            if (expectedException != null && expectedException.isAssignableFrom(err.getCause().getClass())) {
                success++;
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
        return success;
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
