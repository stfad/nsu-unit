package Tests;

import ClassesToTest.BounceFM;
import ClassesToTest.LosSantos;
import ClassesToTest.RadioStation;
import ClassesToTest.RadioX;
import ClassesToTest.SimpleRadioFactory;
import Core.After;
import Core.Assert;
import Core.Before;
import Core.ClassToTest;
import Core.Test;

public class SimpleRadioFactoryTest extends ClassToTest {
    @Before
    public void initialize()  {
        System.out.println("initializing environment.");
    }

    @After
    public void clean() {
        System.out.println("------------------------------------------------------------");
    }

    @Test
    public void createFunkStation() {
        RadioStation radio = SimpleRadioFactory.createRadioStation("funk");
        Assert.assertTrue(radio instanceof BounceFM);
    }

    @Test
    public void createHipHopStation() {
        RadioStation radio = SimpleRadioFactory.createRadioStation("hip hop");
        Assert.assertTrue(radio instanceof LosSantos);
    }

    @Test
    public void createHardRockStation() {
        RadioStation radio = SimpleRadioFactory.createRadioStation("hard rock");
        Assert.assertTrue(radio instanceof RadioX);
    }
}
