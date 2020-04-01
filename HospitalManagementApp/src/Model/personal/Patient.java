package Model.personal;

import Model.tratament.Prescription;
import java.util.Objects;

public class Patient extends Person
{
    private boolean insured;
    private String CNP;
    private Prescription prescription;

    public Patient()
    {
        super();
        CNP = "";
        insured = false;
        prescription = new Prescription();
    }

    public Patient(String lastName , String firstName , String cnp , boolean insured)
    {
        super(lastName , firstName);
        this.CNP = cnp;
        this.insured = insured;
        prescription = new Prescription();
    }

    public boolean getInsured()
    {
        return insured;
    }

    public String getCNP()
    {
        return CNP;
    }

    public Prescription getPrescription()
    {
        return prescription;
    }

    public void setInsured(boolean insured)
    {
        this.insured = insured;
    }

    public void setCNP(String CNP)
    {
        this.CNP = CNP;
    }

    public void setPrescription(Prescription r){this.prescription = r;}

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals((Person)o)) return false;
        Patient patient = (Patient) o;
        return getInsured() == patient.getInsured() &&
                Objects.equals(getCNP(), patient.getCNP()) &&
                Objects.equals(getPrescription(), patient.getPrescription());
    }

    @Override
    public String toString()
    {
        return "Pacient { " +
                "insured = " + insured +
                ", CNP = '" + CNP + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", prescription = " + prescription +
                " } ";
    }
}

