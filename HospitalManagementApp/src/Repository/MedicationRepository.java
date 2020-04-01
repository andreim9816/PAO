package Repository;

import Model.tratament.Medication;
import com.sun.java.swing.plaf.motif.MotifEditorPaneUI;

import java.util.ArrayList;

public class MedicationRepository
{
    private ArrayList<Medication> medicationDB;

    public MedicationRepository()
    {
        /**
         * Constructor
         */

        medicationDB = new ArrayList<>();
    }

    public void add(Medication m)
    {
        /**
         * Function that adds a Medicament in the database
         */

        medicationDB.add(m);
    }

    public void remove(Medication m)
    {
        /**
         * Funtion that removes a specific Medicament from the Database, otherwise just prints a message
         */

        boolean var = medicationDB.remove(m);
        if(!var)
            System.out.println(m + " was not removed because it doesn't exist");
    }

    public boolean isPrescribed(Medication m)
    {
        /**
         * Function that checks if a specific medicament is prescribed
         */

        for(Medication med : medicationDB)
            if(med.getName().equals(m.getName()))
                return true;
        return false;
    }

    public Medication getMedicationReference(Medication M)
    {
        for(Medication m : medicationDB)
            if(m.equals(M))
                return m;
        return null;
    }
    public ArrayList<Medication> getMedicamentByName(String name)
    {
        /**
         * Function that returns a list of Medicament objects that are of a specific name
         */
        ArrayList<Medication> result = new ArrayList<>();

        for(Medication m : medicationDB)
            if(m.getName().equals(name))
                result.add(m);

        return result;
    }

    public ArrayList<Medication> getAllMedicament()
    {
        return medicationDB;
    }


}
