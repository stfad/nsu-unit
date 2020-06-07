package Tests;

import Core.ClassToTest;
import Core.Test;

public class MultithreadTest2 extends ClassToTest {
    @Test
    public void twoMinutesLoop() throws InterruptedException {
        for (int i = 0; i < 120; ++i) {
            if (i%3 == 0)
                System.out.println("[thread" + Thread.currentThread().getId() + "]: "  + i);
            Thread.sleep(1000);
        }
    }
}
