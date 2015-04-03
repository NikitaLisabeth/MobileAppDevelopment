package be.howest.nmct;

/**
 * Created by Nikita on 13/02/2015.
 */
public class StopAfstandInfo {

    public StopAfstandInfo(){
        //constructor
    }


    //Attributen met hun get- en setmethode.
    private int snelheid = 0;
    public int getSnelheid(){
        return snelheid;
    }
    public void setSnelheid(int snelheid){
        this.snelheid = snelheid;
    }

    private float reactietijd = 0;
    public float getReactietijd(){
        return reactietijd;
    }
    public void setReactietijd(float reactietijd){
        this.reactietijd = reactietijd;
    }

    private float stopafstand = 0;
    public float getStopafstand(){
        return stopafstand;
    }
    public void setStopafstand(float stopafstand){
        this.stopafstand = stopafstand;
    }

    private WegType wegType;
    public WegType getWegType(){
        return wegType;
    }
    public void setWegType(WegType wegType){
        this.wegType = wegType;
    }

    public enum WegType{
        WEGDEK_DROOG, WEGDEK_NAT
    }

    public float getStopAfstand(int snelh, float reactiet,WegType wegt){
        double stopafst = 0;
        double snelheidMS = snelh / 3.6;
        int remvertraging = 0;

        switch (wegt) {
            case WEGDEK_DROOG:
                remvertraging = 8;
                break;

            case WEGDEK_NAT:
                remvertraging = 5;
                break;
        }
        float snelheidFloat = (float)snelheidMS;

        stopafst = snelheidFloat * reactiet + Math.pow(snelheidFloat,2)/(2*remvertraging);
        return (float)stopafst;
    }
    @Override
    public String toString() {
        return "De stopafstand is "+stopafstand + " m/s.";
    }
}
