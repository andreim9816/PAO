package Model.administrativ;
public class Department
{
    private String nameDepartment;
    private int noOfBeds;
    private int idHospital;

    public Department()
    {
        nameDepartment = "";
        noOfBeds = 0;
        idHospital = 0;
    }

    public Department(Department s)
    {
        nameDepartment = new String(s.nameDepartment);
        noOfBeds = s.noOfBeds;
        idHospital = s.idHospital;
    }

    public Department(String nameDepartment, int noOfBeds)
    {
        this.nameDepartment = new String(nameDepartment);
        this.noOfBeds = noOfBeds;
    }

    public String getNameDepartment()
    {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment)
    {
        this.nameDepartment = new String(nameDepartment);
    }

    public int getNoOfBeds()
    {
        return noOfBeds;
    }

    public void setNoOfBeds(int noOfBeds)
    {
        this.noOfBeds = noOfBeds;
    }

    public int getIdHospital()
    {
        return idHospital;
    }

    public void setIdHospital(int idHospital)
    {
        this.idHospital = idHospital;
    }

    @Override
    public String toString()
    {
        return "Department{" +
                "nameDepartment='" + nameDepartment + '\'' +
                ", noOfBeds=" + noOfBeds +
                ", idHospital=" + idHospital +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department d = (Department) o;
        return (d.getIdHospital() == getIdHospital() && d.getNameDepartment().equals(getNameDepartment()));
    }

}
