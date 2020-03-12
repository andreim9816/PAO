package probleme12345;

public class Complex
{
    private double re, im;

    public Complex(double re, double im)
    {
        this.re = re;
        this.im = im;
    }

    public Complex()
    {
        this(0.0 , 0.0);
    }

    public Complex sum(Complex z)
    {
        Complex sum = new Complex();

        sum.re = re + z.re;
        sum.im = im + z.im;

        return sum;
    }

    @Override
    public String toString()
    {
        String result = new String();

        if(re == 0)
            if(im == 0)
            {
                result = "0";
            }
            else result = im + "i";
        else
        {
            if(im == 0)
                result = re + "";
            else if(im > 0)
                result = re + " + " + im + "i";
            else result = re + " " + im + "i";
        }

        return result;
    }

    public boolean equals(Complex z)
    {
        if(re == z.re && im == z.im)
            return true;
        return false;
    }

    public static void main(String[] args)
    {
        Complex z = new Complex(0.0, 2.5);
        Complex z1 = new Complex(13.0 ,55.0);

        System.out.println(z.toString());
        System.out.println(z1.toString());

        System.out.println(z1.sum(z).toString());
        System.out.println(z.equals(z1));

    }

}
