package Tests;

import ClassesToTest.BounceFM;
import ClassesToTest.RadioStation;
import Core.After;
import Core.Assert;
import Core.Before;
import Core.ClassToTest;
import Core.Test;

public class BounceFMTest extends ClassToTest {
    @Test
    public void constructorTest() {
        BounceFM bounceFM1 = new BounceFM(), bounceFM2 = new BounceFM();
        bounceFM1.setName("Bounce FM");
        bounceFM1.setHost("The Funktipus");
        bounceFM1.setGenre("funk");
        Assert.assertEquals(bounceFM1, bounceFM2);
    }

    @Before
    public void initialize() {
        System.out.println("initializing environment.");
    }

    @After
    public void clean() {
        System.out.println("------------------------------------------------------------");
    }

    @Test
    public void orderSongCorrect() {
        String songName = "Cameo - Candy";
        BounceFM bounceFM = new BounceFM();
        try {
            bounceFM.orderSong(songName);
        } catch (RadioStation.SongNotFoundException err) {
            Assert.assertTrue(false);
        }
        Assert.assertIntegerEquals(bounceFM.getSongsInQueue(),1);
    }

    @Test
    public void orderTwoSongsCorrect() {
        String songName1 = "Cameo - Candy";
        String songName2 = "Cameo - Candy";
        BounceFM bounceFM = new BounceFM();
        try {
            bounceFM.orderSong(songName1);
            bounceFM.orderSong(songName2);
        } catch (RadioStation.SongNotFoundException err) {
            Assert.assertTrue(false);
        }
        Assert.assertIntegerEquals(bounceFM.getSongsInQueue(),2);
    }

    @Test(expected = RadioStation.SongNotFoundException.class)
    public void orderSongNotFoundException() throws RadioStation.SongNotFoundException {
        String songName = "Cameo - Candy1";
        BounceFM bounceFM = new BounceFM();
        bounceFM.orderSong(songName);
        Assert.assertTrue(false);
    }
}
