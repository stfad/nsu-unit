package Tests;

import Core.ClassToTest;
import Core.Test;

public class MultithreadTest1 extends ClassToTest {
    @Test
    public void oneMinuteLoop() throws InterruptedException {
        for (int i = 0; i < 60; ++i) {
            if (i%2 == 0)
                System.out.println("[thread" + Thread.currentThread().getId() + "]: "  + i);
            Thread.sleep(1000);
        }
    }
}
