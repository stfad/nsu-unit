package ClassesToTest;

public class SimpleRadioFactory {
    public RadioStation createRadioStation(String type) throws Exception {
        RadioStation radioStation = null;
        if (type.equals("funk")) {
            radioStation = new BounceFM();
        } else if (type.equals("hip-hop")) {
            radioStation = new LosSantos();
        } else if (type.equals("hard rock")) {
            radioStation = new RadioX();
        } else {
            throw new Exception("Unknown radio type");
        }
        return radioStation;
    }
}