package ClassesToTest;

import java.util.ArrayList;

public class RadioX extends RadioStation {
    public RadioX() {
        super("Radio X", "DJ SAGE", "Hard rock", new ArrayList<>());
        availableSongs.add("Ozzy Osbourne - Hellraiser");
        availableSongs.add("Danzig - Mother");
        availableSongs.add("Depeche Mode - Personal Jesus");
    }
}
