package Core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassToTest {
    void runOneTest(Method test) {
        try {
            if (beforeMethod != null) {
                beforeMethod.invoke(this);
            }
            System.out.println(test.getName() + " STARTED");
            test.invoke(this);
            System.out.println(test.getName() + " PASSED");
        } catch (InvocationTargetException err) {
            System.out.println("1");
            Class<? extends Throwable> expectedException;
            Test annotation = test.getAnnotation(Test.class);
            System.out.println("2");
            if (annotation == null || annotation.expected() == Test.EmptyException.class) {
                System.out.println("3");
                expectedException = null;
            } else {
                System.out.println("4");
                expectedException = annotation.expected();
            }

            if (expectedException != null && expectedException.isAssignableFrom(err.getCause().getClass())) {
                System.out.println("5");
                System.out.println(test.getName() + " PASSED");
            } else {
                System.out.println("6");
                System.out.println("THERE WAS A FAILURE: " + err.getCause().toString());
            }
        } catch (Throwable err) {
            System.out.println("7");
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
    }

    public void updateBeforeMethod(Method m) throws BeforeMethodAlreadyExistsException {
        if (beforeMethod != null) {
            throw new BeforeMethodAlreadyExistsException("@Before annotation should be used only once!");
        }
        beforeMethod = m;
    }

    public void updateAfterMethod(Method m) throws AfterMethodAlreadyExistsException {
        if (afterMethod != null) {
            throw new AfterMethodAlreadyExistsException("@After annotation should be used only once!");
        }
        afterMethod = m;
    }

    private Method beforeMethod, afterMethod;
}
