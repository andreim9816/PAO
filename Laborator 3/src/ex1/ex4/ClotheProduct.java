package ex4;

public class ClotheProduct extends Product
{
    private String type;

    ClotheProduct()
    {
        type = "";
    }

    ClotheProduct(String type)
    {
        super();
        this.type = type;
    }

    ClotheProduct(double price , String name , String colour , String type)
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
