package Model.personal;

public class Nurse extends Person
{
    public Nurse(String lastName, String firstName, String CNP)
    {
        super(lastName, firstName, CNP);
    }

    public Nurse(Person p)
    {
        super(p);
    }

    @Override
    public String toString()
    {
        return "Nurse{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", CNP=" + CNP +
                ", idHospital=" + idHospital +
                ", nameDepartment='" + nameDepartment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj)
    {
        return super.equals(obj);
    }
}
