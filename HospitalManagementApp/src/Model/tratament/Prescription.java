package Model.tratament;

import java.util.Vector;

public class Prescription
{
    private String patientFirstName;
    private String patientLastName;
    private String recommendation;
    private Vector<Medication> arrayMedication;
    private static int noOfObjects = 0;
    private int ID;
    {
        noOfObjects++;
    }

    public Prescription( )
    {
        patientFirstName = patientLastName = recommendation = "";
        arrayMedication = new Vector();
        ID = 0;
    }

    public String getPatientFirstName()
    {
        return patientFirstName;
    }

    public String getPatientLastName()
    {
        return patientLastName;
    }

    public String getRecommendation()
    {
        return recommendation;
    }

    public Vector<Medication> getArrayMedication()
    {
        return arrayMedication;
    }

    public int getID()
    {
        return ID;
    }

    public void setPatientLastName(String patientLastName)
    {
        this.patientLastName = patientLastName;
    }

    public void setPatientFirstName(String patientFirstName)
    {
        this.patientFirstName = patientFirstName;
    }

    public void setRecommendation(String recommendation)
    {
        this.recommendation = recommendation;
    }

}
