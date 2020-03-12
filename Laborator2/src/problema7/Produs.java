package problema7;

public class Produs
{
    String nume;
    double pret;
    int cantitate;

    Produs()
    {
        nume = "";
        pret = 0.0;
        cantitate = 0;
    }

    Produs(String nume , double pret , int cantitate)
    {
        this.nume = nume;
        this.pret = pret;
        this.cantitate = cantitate;
    }

    @Override
    public String toString()
    {
        String result = "Produs: " + nume + "; pret: " + pret + "; cantitate: " + cantitate;
        return result;
    }

    public double getTotalProdus()
    {
        return pret * cantitate;
    }

    public static void main(String[] args)
    {

    }
}
