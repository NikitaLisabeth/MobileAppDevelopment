package be.howest.nmct.politiecontroles.models;

import java.util.List;

/**
 * Created by Nikita on 20/05/2015.
 */
public class SnelheidsControle {
    public String Maand;
    public String Straat;
    public String Postcode;
    public String Gemeente;
    public String Aantal;
    public String Gpasseerde_voertuigen;
    public String InOverTreding;
    public String X;
    public String Y;


    public SnelheidsControle(){
        this.Maand="";
        this.Straat="";
        this.Postcode="";
        this.Gemeente="";
        this.Aantal="";
        this.Gpasseerde_voertuigen="";
        this.InOverTreding="";
        this.X="";
        this.Y="";
    }

    public static List<SnelheidsControle> Controles;
}
