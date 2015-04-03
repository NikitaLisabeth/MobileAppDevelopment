package nmct.howest.be.bmicalculator;

/**
 * Created by Nikita on 13/02/2015.
 */
public class BMIInfo {
    public  BMIInfo(){}

    private float bmiIndex;
    private float height = (float)1.70;
    private float mass = 70;
    private Category category = Category.NORMAAL;

    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }

    public float getMass() {
        return mass;
    }
    public void setMass(float mass) {
        this.mass = mass;
    }

    public Category getCategory() {
        return category;
    }

    public float getBmiIndex() {
        return bmiIndex;
    }
    public void setBmiIndex(float bmiIndex) {
        this.bmiIndex = bmiIndex;
    }

    public void calculateBMI(){
        //bmi berekenen en het attribuut instellen.
        bmiIndex = mass / (height*height);
        category = Category.getCategory(bmiIndex);
    }

    //constante --> hoofdletter
    public enum Category{
        GROOT_ONDERGEWICHT(0,15),
        ERNSTIG_ONDERGEWICHT(15,16),
        ONDERGEWICHT(16,18.5f),
        NORMAAL(18.5f,25),
        OVERGEWICHT(25,30),
        MATIG_OVERGEWICHT(30,35),
        ERNSTIG_OVERGEWICHT(35,40),
        ZEER_GROOT_OVERGEWICHT(40,100);

        private float lowerBoundary;
        private float upperBoundary;

        Category(float lowerBoundary, float upperBoundary) {
            this.lowerBoundary = lowerBoundary;
            this.upperBoundary = upperBoundary;
        }
        public boolean isInBoundary(float index){
            if((index >= getLowerBoundary()) && (index < getUpperBoundary()) ){
                return  true;
            }
            return false;
        }

        //static methode in een enumeration.
        public static Category getCategory(float index) {
            for(Category category : Category.values()) {
                if(category.isInBoundary(index)) return category;
            }
            return null;
        }

        public float getLowerBoundary() {
            return lowerBoundary;
        }

        public float getUpperBoundary() {
            return upperBoundary;
        }

    }
}
