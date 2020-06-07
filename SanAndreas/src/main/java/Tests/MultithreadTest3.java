package Tests;

import Core.ClassToTest;
import Core.Test;

public class MultithreadTest3 extends ClassToTest {
    @Test
    public void threeMinutesLoop() throws InterruptedException {
        for (int i = 0; i < 180; ++i) {
            if (i%5 == 0)
                System.out.println("[thread" + Thread.currentThread().getId() + "]: "  + i);
            Thread.sleep(1000);
        }
    }
}
