package ex4;

public abstract class Product
{
    protected double price;
    protected String name;
    protected String colour;

    public Product()
    {
        price = 0.0;
        name = colour = "";
    }

    public Product(double price , String name , String colour)
    {
        this.price = price;
        this.name = name;
        this.colour = colour;
    }

    public abstract void roundPrice();
    public abstract double getPrice();
    public abstract String getName();
    public abstract String getColour();

    @Override
    public String toString()
    {
        return "Price: " + price + " Name: " + name + " Colour: " + colour + " ";
    }
}
