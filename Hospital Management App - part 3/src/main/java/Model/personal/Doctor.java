package Model.personal;

import java.io.BufferedWriter;
import java.io.IOException;

public class Doctor extends Person
{
    private String grade;

    public Doctor(String lastName, String firstName, String CNP, String grade, int idHospital, String nameDepartment)
    {
        super(lastName, firstName, CNP);
        setGrade(grade);
        setIdHospital(idHospital);
        setNameDepartment(nameDepartment);
    }

    public Doctor(String lastName, String firstName, String CNP, String grade)
    {
        super(lastName, firstName, CNP);
        this.grade = grade;
    }

    public Doctor(Person p, String grade)
    {
        super(p);
        this.grade = grade;
    }

    public String getGrade()
    {
        return grade;
    }

    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    @Override
    public String toString()
    {
        return "Doctor{" +
                "grade='" + grade + '\'' +
                ", lastName='" + lastName + '\'' +
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
        bufferedWriter.write("," + ((Doctor) person).getGrade());
        bufferedWriter.write("\r\n");
    }

    @Override
    public boolean equals(Object obj)
    {
        return super.equals(obj);
    }
}
