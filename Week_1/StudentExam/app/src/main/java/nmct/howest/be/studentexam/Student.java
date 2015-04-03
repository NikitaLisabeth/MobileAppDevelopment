package nmct.howest.be.studentexam;

import org.w3c.dom.ls.LSException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nikita on 13/02/2015.
 */
public class Student {

    String emailStudent;
    HashMap <String, ModulePunt> scoreStudent;

    public String getEmailStudent() {
        return emailStudent;
    }
    public void setEmailStudent(String emailStudent) {
        this.emailStudent = emailStudent;
    }

    public void voegScoreToe(String sModuleNaam, double score){
        ModulePunt m = new ModulePunt();
        m.score = score;
        m.moduleNaam = sModuleNaam;
        m.aantalStudiePunten = 6;

        scoreStudent.put(sModuleNaam,m);
    }

    public void getTotaleScoreStudent(){
        int totaalStudiepunten = 0;
        double moduleGewicht=0;
        double totaalPercentage;
        int sPunt =0;
        moduleGewicht = sPunt / totaalStudiepunten;

        for(ModulePunt m : scoreStudent.values()){
            totaalStudiepunten+= m.aantalStudiePunten;
            totaalPercentage = m.score * moduleGewicht;
            sPunt = m.aantalStudiePunten;
        }


    }


    @Override
    public String toString() {
        return super.toString();
    }

    public static List<Double> getScoresModule(List<Student> studenten, String moduleNaam){
        List<Double> resultaten = new List<Double>();
        return resultaten;
    }

    public static double getGemiddeldeScoreModule(List<Student>, String moduleNaam){
        double gemiddelde = 0;
        return gemiddelde;
    }

    public static void sorteerStudenten(List<Student> studenten){

    }

}
