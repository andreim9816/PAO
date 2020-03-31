package Model.tratament;

import java.util.Objects;

public class Dosage
{
    private int quantity;
    private String specs;

    public Dosage()
    {
         quantity = 0;
        specs = "";
    }
    public Dosage(int  quantity, String specs)
    {
        this. quantity = quantity;
        this.specs = specs;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public String getSpecs()
    {
        return specs;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public void setspecs(String specs)
    {
        this.specs = specs;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dosage dosage = (Dosage) o;
        return getQuantity() == dosage.getQuantity() &&
                Objects.equals(getSpecs(), dosage.getSpecs());
    }

    @Override
    public String toString() {
        return "Dozaj { " +
                " quantity = " + quantity +
                ", specs = '" + specs + '\'' +
                " } ";
    }
}
