package nmct.howest.be.bmicalculator;

import java.util.Scanner;

/**
 * Created by Nikita on 13/02/2015.
 */
public class BMIInfoTest {
    public static void main(String [] args){
        System.out.println("Demo bmi");

        //alternatief: printwriter

        //inlezen van getallen
        Scanner sc = new Scanner(System.in);

        System.out.println("Geef uw gewicht in. (in kg)");
        float mass = sc.nextFloat();
        System.out.println("Geef uw lengte in. (in meter)");
        float height = sc.nextFloat();

        BMIInfo bmi = new BMIInfo();
        bmi.setHeight(height);
        bmi.setMass((float)mass);
        bmi.calculateBMI();
        float mijnBMI = bmi.getBmiIndex();
        BMIInfo.Category mijnCategorie = BMIInfo.Category.getCategory(mijnBMI);
        System.out.println("Uw BMI bedraagt "+mijnBMI+", U bevind zich in de categorie "+ mijnCategorie);
    }
}
