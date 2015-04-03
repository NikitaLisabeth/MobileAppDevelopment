package be.howest.nmct.bmi;

public class BMIInfo {

    private float height = 1.70f;
    private int mass = 70;
    private Category category = Category.NORMAL;

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public BMIInfo(float height, int mass) {
        this.height = height;
        this.mass = mass;
    }

    public static enum Category {

        LARGE_UNDERWEIGHT (0 , 15),
        MEDIUM_UNDERWEIGHT (15, 16),
        UNDERWEIGHT (16, 18.5f),
        NORMAL (18.5f, 25),
        OVERWEIGHT (25, 30),
        MODERATE_OVERWEIGHT (30, 35),
        MEDIUM_OVERWEIGHT (35, 40),
        LARGE_OVERWEIGHT (40, 299);

        private float lowerBoundary;
        private float upperBoundary;

        Category(float lowerBoundary,float upperBoundary){
            this.lowerBoundary = lowerBoundary;
            this.upperBoundary = upperBoundary;
        }

        public boolean isInBoundary(float index){
            if(index > this.lowerBoundary && index < this.upperBoundary){
                return true;
            }
            return false;
        }

        public static Category getCategory(float index){
            for(Category category : Category.values()){
                if(category.isInBoundary(index)) return category;
            }
            return null;
        }
    }

    public float Recalculate(){
        return  this.mass / (float) (Math.pow(this.height, 2));
    }
}
