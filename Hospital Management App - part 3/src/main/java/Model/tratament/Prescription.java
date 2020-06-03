package Model.tratament;

import Model.Utils.WriteFile;

import java.io.BufferedWriter;
import java.io.IOException;

public class Prescription implements WriteFile<Prescription>
{
    private String CNPPatient;
    private String CNPDoctor;
    private String recommendation;
    private int idPrescription = 0;

    public Prescription(int id, String cnpPatient, String cnpDoctor, String recommendation)
    {
        setCNPDoctor(cnpDoctor);
        setCNPPatient(cnpPatient);
        setRecommendation(recommendation);
        setIdPrescription(id);
    }

    public Prescription(String cnpPatient, String cnpDoctor, String recommendation)
    {
        setCNPDoctor(cnpDoctor);
        setCNPPatient(cnpPatient);
        setRecommendation(recommendation);
    }

    public String getCNPPatient()
    {
        return CNPPatient;
    }

    public void setCNPPatient(String CNPPatient)
    {
        this.CNPPatient = CNPPatient;
    }

    public String getCNPDoctor()
    {
        return CNPDoctor;
    }

    public void setCNPDoctor(String CNPDoctor)
    {
        this.CNPDoctor = CNPDoctor;
    }

    public int getIdPrescription()
    {
        return idPrescription;
    }

    public void setIdPrescription(int idPrescription)
    {
        this.idPrescription = idPrescription;
    }

    public String getRecommendation()
    {
        return recommendation;
    }

    public void setRecommendation(String recommendation)
    {
        this.recommendation = recommendation;
    }

    public void print(BufferedWriter bufferedWriter)  throws IOException
    {
       bufferedWriter.write(getIdPrescription() + "," + getCNPDoctor() + "," + getCNPPatient() +"," + getRecommendation() + "\r\n");
    }

    @Override
    public String toString()
    {
        return "Prescription{" +
                "idPrescription=" + idPrescription +
                ", Emis de doctorul cu CNP = " + CNPDoctor + '\'' +
                ", Pentru pacientul cu CNP = " + CNPPatient + '\'' +
                ", Recommendation = " + recommendation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prescription that = (Prescription) o;
        return (getIdPrescription() == that.getIdPrescription());
    }

}
