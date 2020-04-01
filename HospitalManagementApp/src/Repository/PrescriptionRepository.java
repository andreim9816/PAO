package Repository;

import Model.tratament.Medication;
import Model.tratament.Prescription;

import java.util.ArrayList;

public class PrescriptionRepository
{
    private ArrayList<Prescription> prescriptionDB;

    public PrescriptionRepository()
    {
        /**
         * Constructor
         */
        prescriptionDB = new ArrayList<>();
    }

    public ArrayList<Prescription> getPrescriptionAll()
    {
        return prescriptionDB;
    }

    public void add(Prescription r)
    {
        /**
         * Function that adds a speicific Reteta object
          */

        prescriptionDB.add(r);
    }

    public void remove(Prescription r)
    {
        /**
         * Funtion that removes a specific Reteta from the Database, otherwise just prints a message
         */

        boolean var = prescriptionDB.remove(r);
        if(!var)
            System.out.println(r + " was not removed because it doesn't exist");
    }

    public Prescription getPrescriptionReference(Prescription P)
    {
        for(Prescription p : prescriptionDB)
            if(p.equals(P))
                return p;
        return null;
    }

    public void removeByName(String lastName, String firstName)
    {
        /**
         * Function that removes all the Reteta objects that are acquired by a specific patient
         */
        boolean value = false;

        for(Prescription r : prescriptionDB)
            if(r.getPatientLastName().equals(lastName) && r.getPatientFirstName().equals(firstName))
            {
                prescriptionDB.remove(r);
                value = true;
            }

        if(value == false)
            System.out.println("No Reteta object was removed because it doesn't exist");
    }

    public void removeById(int ID)
    {
        /**
         * Function that removes a specific Reteta object, identiied by its ID
         */

        boolean value = false;
        for(Prescription r : prescriptionDB)
            if(r.getID() == ID)
            {
                prescriptionDB.remove(r);
                value = true;
                break;
            }

        if(value == false)
            System.out.println("No Reteta object was removed because it doesn't exist");
    }
}
