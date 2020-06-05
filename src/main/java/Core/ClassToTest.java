package Core;

import java.lang.reflect.Method;

public class ClassToTest {
    void runOneTest(Method test) {
        try {
            test.invoke(this);
            System.out.println(test.getName() + " PASSED");
        } catch (Throwable err) {
            System.out.println(test.getName() + " FAILED BY " + err.toString());
        }
    }
}
