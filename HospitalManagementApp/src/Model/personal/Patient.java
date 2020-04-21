package Model.personal;

import java.io.BufferedWriter;
import java.io.IOException;

public class Patient extends Person {
    private boolean insured;

    public Patient(String lastName, String firstName, String CNP, boolean insured)
    {
        super(lastName, firstName, CNP);
        this.insured = insured;
    }

    public Patient(Person p, boolean insured)
    {
        super(p);
        this.insured = insured;
    }

    public boolean getInsured() {
        return insured;
    }

    public void setInsured(boolean insured) {
        this.insured = insured;
    }

    @Override
    public String toString()
    {
        return "Patient{" +
                "insured=" + insured +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", CNP='" + CNP + '\'' +
                ", idHospital=" + idHospital +
                ", nameDepartment='" + nameDepartment + '\'' +
                '}';
    }

    public void print(BufferedWriter bufferedWriter) throws IOException
    {
        Person person = this;
        bufferedWriter.write(person.getCNP() + "," + person.getLastName() + "," +  person.getFirstName() + "," + person.getIdHospital() + "," +  person.getNameDepartment());
        bufferedWriter.write("," + ((Patient) person).getInsured());
        bufferedWriter.write("\r\n");

    }

    @Override
    public boolean equals(Object obj)
    {
        return super.equals(obj);
    }
}

