package Model.administrativ;

import Model.personal.Doctor;
import Model.personal.Patient;
import Model.personal.Person;

import java.util.Objects;
import java.util.Vector;

public class Department
{
    private String name;
    private int noOfBeds;
    private Vector<Person>arrayPatient;
    private Vector<Person>arrayDoctor;

    public Department()
    {
        name = "";
        noOfBeds = 0;
        arrayDoctor = new Vector();
        arrayPatient = new Vector();
    }

    public Department(Department s)
    {
        name = new String(s.name);
        noOfBeds = s.noOfBeds;
    }

    public Department(String name, int noOfBeds)
    {
        this.name = name;
        this.noOfBeds = noOfBeds;
        arrayDoctor = new Vector();
        arrayPatient = new Vector();
    }

    public Vector<Person> getArrayPatient()
    {
        return arrayPatient;
    }

    public Vector<Person> getArrayDoctor()
    {
        return arrayDoctor;
    }

    public String getName()
    {
        return name;
    }

    public int getNoOfBeds()
    {
        return noOfBeds;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setNoOfBeds(int noOfBeds)
    {
        this.noOfBeds = noOfBeds;
    }

    @Override
    public String toString()
    {
        return "Department { " +
                "name = '" + name + '\'' +
                ", noOfBeds = " + noOfBeds +
                ", arrayPatient = " + arrayPatient +
                ", arrayDoctor = " + arrayDoctor +
                " } ";
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return getNoOfBeds() == that.getNoOfBeds() &&
                Objects.equals(getName(), that.getName());
    }

}
