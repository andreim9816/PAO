package ex3;

public class Circle
{
    private double radius;
    private String colour;

    public Circle()
    {
        radius = 1.0;
        colour = "Red";
    }

    public Circle(double rad)
    {
        this();
        radius = rad;
    }

    public void setRadius(double rad)
    {
        radius = rad;
    }

    public Circle(double rad , String col)
    {
        radius = rad;
        colour = col;
    }

    public double getRadius()
    {
        return radius;
    }

    public void setColour(String col)
    {
        colour = col;
    }

    public double getArea()
    {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString()
    {
        return "The circle is " + colour + " and its radius is " + radius;
    }

    @Override
    public boolean equals(Object obj)
    {
        Circle aux = (Circle)obj;
        return (aux.colour == this.colour && radius == aux.radius);
    }
}
