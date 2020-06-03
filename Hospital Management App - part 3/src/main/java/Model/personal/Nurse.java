package Model.personal;

import javax.swing.plaf.ViewportUI;
import java.io.BufferedWriter;
import java.io.IOException;

public class Nurse extends Person
{
    public Nurse(String lastName, String firstName, String CNP)
    {
        super(lastName, firstName, CNP);
    }

    public Nurse(String lastName, String firstName, String CNP, int idHospital, String nameDepartment)
    {
        super(lastName, firstName, CNP);
        setIdHospital(idHospital);
        setNameDepartment(nameDepartment);
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

    public void print(BufferedWriter bufferedWriter) throws IOException
    {
        Person person = this;
        bufferedWriter.write(person.getCNP() + "," + person.getLastName() + "," +  person.getFirstName() + "," + person.getIdHospital() + "," +  person.getNameDepartment());
        bufferedWriter.write("\r\n");
    }
    @Override
    public boolean equals(Object obj)
    {
        return super.equals(obj);
    }
}
