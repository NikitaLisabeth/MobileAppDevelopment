package be.howest.nmct;

import junit.framework.Test;

import java.util.Scanner;

/**
 * Created by Nikita on 13/02/2015.
 */
public class TestStopAfstandInfo {

    public static void main(String [] args){
        System.out.println("Dit is mijn eerste lijn Java");


        StopAfstandInfo stopAfstand1 = new StopAfstandInfo();
        StopAfstandInfo stopAfstand2 = new StopAfstandInfo();
        StopAfstandInfo stopAfstand3 = new StopAfstandInfo();

        Scanner sc = new Scanner(System.in);
        System.out.println("Geef de snelheid in.");
        int snelheid = sc.nextInt();
        System.out.println("Geef de reactietijd in.");
        float reactietijd = sc.nextFloat();
        System.out.println("Is het wegdek DROOG of NAT?");
        String wt = sc.next();
        StopAfstandInfo.WegType wegType;

        switch (wt) {
            case "NAT":
                wegType =  StopAfstandInfo.WegType.WEGDEK_NAT;
                break;

            case "DROOG":
                wegType = StopAfstandInfo.WegType.WEGDEK_DROOG;
                break;
        }
        /*if(wt == "Nat"){
            wegType = StopAfstandInfo.WegType.WEGDEK_NAT;
        }else{
            wegType = StopAfstandInfo.WegType.WEGDEK_DROOG;
        }*/
        stopAfstand1.setStopafstand(stopAfstand1.getStopAfstand(snelheid, reactietijd, wegType));


        System.out.println(stopAfstand1.toString());
    }

   /* public TestStopAfstandInfo(){
        StopAfstandInfo stopAfstand1 = new StopAfstandInfo();
        StopAfstandInfo stopAfstand2 = new StopAfstandInfo();
        StopAfstandInfo stopAfstand3 = new StopAfstandInfo();

        Scanner sc = new Scanner(System.in);
        System.out.println("Geef de snelheid in.");
        int snelheid = sc.nextInt();
        System.out.println("Geef de reactietijd in.");
        float reactietijd = sc.nextFloat();
        System.out.println("Is het wegdek droog of nat?");
        String wt = sc.next();
        StopAfstandInfo.WegType wegType;

        if(wt == "Nat"){
            wegType = StopAfstandInfo.WegType.WEGDEK_NAT;
        }else{
            wegType = StopAfstandInfo.WegType.WEGDEK_DROOG;
        }

        stopAfstand1.getStopAfstand(snelheid, reactietijd, wegType );
    }*/
}
