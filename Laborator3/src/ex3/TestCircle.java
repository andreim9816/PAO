package ex3;

public class TestCircle
{
    public static void main(String[] args)
    {
        Circle x = new Circle();
        Circle y = new Circle(2.0);
        Circle z = new Circle(3.0 , "Pink");

        Circle yy = new Circle();
        yy.setRadius(2.0);

        if(y.equals(yy))
            System.out.println("The circle look alike");
        else System.out.println("The circles don't match");

        System.out.println("The area of the circle is " + z.getArea());

        System.out.println(z.toString());

    }
}

