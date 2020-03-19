package ex4;

public class FoodProduct extends Product
{
    private String type;

    FoodProduct(String type)
    {
        super();
        type = this.type;
    }

    FoodProduct(double price , String name , String colour, String type)
    {
        super(price , name , colour);
        this.type = type;
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
        return super.toString() + "type: " + type;
    }
}
