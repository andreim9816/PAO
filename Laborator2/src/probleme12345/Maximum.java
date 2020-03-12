package probleme12345;

public class Maximum
{
    private int number;

    Maximum (int nr)
    {
        number = nr;
    }

    // OVERLOADING

    public int maxim(int a)
    {
        if(a > number)
            return a;
        return number;
    }

    public int maxim(int a , int b)
    {
        Maximum A = new Maximum(a);
        return this.maxim(A.maxim(b));
    }

    public int maxim(int a , int b , int c)
    {
        Maximum A = new Maximum(a);
        Maximum B = new Maximum(b);

        return this.maxim(A.maxim(B.maxim(c)));
    }

    public int maxim(int a , int b , int c, int d)
    {
        Maximum A = new Maximum(a);
        Maximum B = new Maximum(b);
        Maximum C = new Maximum(c);

        return this.maxim(A.maxim(B.maxim(C.maxim(d))));
    }

    public static void main(String[] args)
    {
        Maximum a = new Maximum(13);
        System.out.println(a.maxim(11,2,5, 9));
    }
}
