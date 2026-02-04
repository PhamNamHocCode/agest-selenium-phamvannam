public class Cricle extends Shape{

    private int diameter;

    @Override
    public float getPerimeter(){
        return (int) (Math.PI * diameter);
    }

    @Override
    public float getArea(){
        double radius = diameter / 2.0;
        return (float) (Math.PI * radius * radius);
    }

    public Cricle (int diameter){
        this.diameter = diameter;
    }
}
