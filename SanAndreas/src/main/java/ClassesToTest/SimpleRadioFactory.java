package ClassesToTest;

import Core.UnknownRadioTypeException;

public class SimpleRadioFactory {
    public static RadioStation createRadioStation(String type) throws UnknownRadioTypeException {
        RadioStation radioStation = null;
        if (type.equals("funk")) {
            radioStation = new BounceFM();
        } else if (type.equals("hip-hop")) {
            radioStation = new LosSantos();
        } else if (type.equals("hard rock")) {
            radioStation = new RadioX();
        } else {
            throw new UnknownRadioTypeException("Unknown radio type! Expected one of: funk, hip-hop, hard rock, received " + type);
        }
        return radioStation;
    }
}