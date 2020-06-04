package ClassesToTest;

import java.util.ArrayList;

public class BounceFM extends RadioStation {
    public BounceFM() {
        super("Bounce FM", "The Funktipus", "funk", new ArrayList<>());
        availableSongs.add("Cameo - Candy");
        availableSongs.add("Dazz Band - Let It Whip");
        availableSongs.add("Fatback Band - Yum Yum (Gimme Some)");
    }
}