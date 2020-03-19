package ex4;

public class FurnitureProduct extends Product
{
    private int height;
    private int width;

    FurnitureProduct()
    {
        height = width = 1;
    }

    FurnitureProduct(int height , int width)
    {
        super();
        this.height = height;
        this.width = width;
    }

    FurnitureProduct(double price , String name , String colour, int height , int width)
    {
        super(price , name , colour);
        this.height = height;
        this.width = width;
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
        return super.toString() + "height: " + height + " width: " + width;
    }
}
