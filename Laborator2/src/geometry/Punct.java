package geometry;

public class Punct
{
    private int x, y;

    Punct()
    {
        x = y = 0;
    }

    Punct (int x , int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    void setX(int x)
    {
        this.x = x;
    }

    void setY(int y)
    {
        this.y = y;
    }

    @Override
    public String toString()
    {
        String result = new String();
        result = "(" + x + "," + y + ")";

        return result;
    }

    public double distance(int x , int y)
    {
        double result;
        double d1, d2;

        d1 = x - this.x;
        d2 = y - this.y;

        result = Math.sqrt(d1 * d1 + d2 * d2);
        return result;
    }

    public double distance(Punct p1)
    {
        return distance(p1.x , p1.y);
    }
}
