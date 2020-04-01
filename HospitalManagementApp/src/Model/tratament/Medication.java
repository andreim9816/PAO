package Model.tratament;

import java.util.Objects;

public class Medication
{
    private String name;
    private Dosage dosage;

    public Medication()
    {
        name = "";
        dosage = new Dosage();
    }

    public Medication(String name, Dosage dosage)
    {
        this.name = name;
        this.dosage = dosage;
    }

    public String getName()
    {
        return name;
    }

    public Dosage getDosage()
    {
        return dosage;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setdosage(Dosage dosage)
    {
        this.dosage = dosage;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medication that = (Medication) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDosage(), that.getDosage());
    }

    @Override
    public String toString()
    {
        return "Medication { " +
                " name = '" + name + '\'' +
                ", dosage = " + dosage +
                " } ";
    }
}
