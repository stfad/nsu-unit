package Tests;

import ClassesToTest.RadioX;
import Core.After;
import Core.Assert;
import Core.Before;
import Core.ClassToTest;
import Core.Test;

public class RadioXTest extends ClassToTest {
    @Test
    public void constructorTest() {
        RadioX radioX = new RadioX(), radioX2 = new RadioX();
        radioX.setName("Bounce FM");
        radioX.setHost("The Funktipus");
        radioX.setGenre("funk");
        Assert.assertEquals(radioX, radioX2);
    }

    @Before
    public void initialize() {
        System.out.println("initializing environment.");
    }

    @After
    public void clean() {
        System.out.println("------------------------------------------------------------");
    }
}
