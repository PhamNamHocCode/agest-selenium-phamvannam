public class Square extends Shape{

    private int height;

    @Override
    public float getPerimeter(){
        return height * 4;
    }

    @Override
    public float getArea(){
        return height * height;
    }

    public Square(int height){
        this.height = height;
    }
}
