package Model.personal;

import Model.Utils.Printable;

import java.util.Objects;

abstract public class Person implements Printable
{
    protected String lastName;
    protected String firstName;
    protected String CNP;
    protected int idHospital;
    protected String nameDepartment;

    static int noOfObjects = 0;
    {
        noOfObjects++;
    }

    public Person(String lastName, String firstName, String CNP)
    {
        this.lastName = lastName;
        this.firstName = firstName;
        this.CNP = CNP;
    }

    public Person(Person p)
    {
        setFirstName(p.firstName);
        setLastName(p.lastName);
        setCNP(p.CNP);
        setIdHospital(p.idHospital);
        setNameDepartment(p.nameDepartment);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getCNP()
    {
        return CNP;
    }

    public void setCNP(String CNP)
    {
        this.CNP = CNP;
    }

    public int getIdHospital()
    {
        return idHospital;
    }

    public void setIdHospital(int idHospital)
    {
        this.idHospital = idHospital;
    }

    public String getNameDepartment()
    {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment)
    {
        this.nameDepartment = nameDepartment;
    }

    public static int getNoOfObjects()
    {
        return noOfObjects;
    }

    public static void setNoOfObjects(int noOfObjects)
    {
        Person.noOfObjects = noOfObjects;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getCNP(), person.getCNP());
    }

}
