package geometry;

public class TestPunct
{
    public static void main(String[] args)
    {
        Punct p1 = new Punct(1 , 3);
        Punct p2 = new Punct( -1 , 2);
        System.out.println(p1.distance(p2));

        // membrii clasei Punct nu pot fi accesati din alta clasa deoarece sunt privati
        //! p1.x = 3;
    }
}
