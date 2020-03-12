package scoala;

public class Catalog
{
    private String grupa;
    private Student s[] = new Student[5];

    public Catalog(String grupa , Student s[])
    {
        this.grupa = grupa;

        for(int i = 0 ; i < 5 ; i++)
            this.s[i] = s[i];
    }
    @Override
    public String toString()
    {
        String res = "Grupa " + grupa + ":\n";
        for(int i = 0 ; i < 5 ; i++)
            res += (s[i].toString() + "\n");

        return res;
    }

    public double getMedieClasa()
    {
        double S = 0;
        int i;

        for(i = 0 ; i < 5 ; i++)
        {
            S += s[i].getMedieAn1();
        }

        return S/5.0;
    }

    public static void main(String[] args)
    {
        Catalog catalog = new Catalog("244" , new Student[]
                {
                new Student("Andrei" , 2.0 , 3.0),
                new Student("Diana" , 9.0 , 3.0),
                new Student("Laura" , 2.0 , 4.0),
                new Student("Hagi" , 10.0 , 10.0),
                new Student("GIGI" , 7.0 , 8.0),
                 });

        System.out.print(catalog.toString());
        System.out.println(catalog.getMedieClasa());
    }
}
