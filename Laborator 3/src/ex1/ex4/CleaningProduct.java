package ex4;

public class CleaningProduct extends Product
{
    private String surface;

    CleaningProduct(String type)
    {
        super();
        this.surface = type;
    }

    CleaningProduct(double price , String name , String colour, String type)
    {
        super(price , name , colour);
        this.surface = type;
    }

    @Override
    public void roundPrice()
    {
        price = Math.round(price);
    }

    @Override
    public double getPrice()
    {
        return price;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getColour()
    {
        return colour;
    }

    @Override
    public String toString()
    {
        return super.toString() + "surface: " + surface;
    }
}
