package Model.administrativ;

public class Hospital
{
    private String name;
    private int idHospital = 0;
    private static int noOfObjects = 0;
    {
        noOfObjects++;
    }

    public Hospital()
    {
        name = "";
        idHospital = noOfObjects;
    }

    public Hospital(String name)
    {
        this.name = new String(name);
        this.idHospital = noOfObjects;
    }

    public Hospital(int idHospital, String name)
    {
        setName(name);
        setIdHospital(idHospital);
    }

    public Hospital(Hospital h)
    {
        setName(h.name);
        setIdHospital(h.idHospital);
    }

    public static void setNoOfObjects(int nr)
    {
        noOfObjects = nr;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = new String(name);
    }

    public int getIdHospital()
    {
        return idHospital;
    }

    public void setIdHospital(int idHospital)
    {
        this.idHospital = idHospital;
    }

    public static int getNoOfObjects()
    {
        return noOfObjects;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hospital hospital = (Hospital) o;
        return (getIdHospital() == hospital.getIdHospital());
    }

    @Override
    public String toString() {
        return "Hospital{" +
                " idHospital=" + idHospital +
                " name='" + name + '\'' +
                '}';
    }
}

