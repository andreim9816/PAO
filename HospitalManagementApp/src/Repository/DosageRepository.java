package Repository;

import Model.tratament.Dosage;

import java.util.ArrayList;

public class DosageRepository
{
    private ArrayList<Dosage> dosageDB;

    public DosageRepository()
    {
        /**
         * Constructor
         */

        dosageDB = new ArrayList<>();
    }

    public void add(Dosage toBeAdded)
    {
        /**
         * Function that adds a Dozaj object toBeAdded
         */

        dosageDB.add(toBeAdded);
    }

    public void changeQuantity(Dosage D , int newQuantity)
    {
        for(Dosage d : dosageDB)
            if(d.equals(D))
                d.setQuantity(newQuantity);
    }

    public void changeQuantity(Dosage D , String newSpecs)
    {
        for(Dosage d : dosageDB)
            if(d.equals(D))
                d.setspecs(newSpecs);
    }

    public Dosage getDosageReference(Dosage D)
    {
        for(Dosage d : dosageDB)
            if(d.equals(D))
                return d;
        return null;
    }

    public ArrayList<Dosage> getDozajByCantity(int min , int max)
    {
        /**
         * Function that returns the objects whose cantity is between min and max values
         */

        ArrayList<Dosage> result = new ArrayList<>();

        for (Dosage dosage : dosageDB)
            if (dosage.getQuantity() >= min && dosage.getQuantity() <= max)
                result.add(dosage);

        return result;

    }

    public void removeDozajByCantity(int min , int max)
    {

        /**
         * Function that removes all the objects whose cantity is between min and max
         */

        for(Dosage dosage : dosageDB)
            if(dosage.getQuantity() >= min && dosage.getQuantity() <= max)
                dosageDB.remove(dosage);
    }

    public void remove(Dosage toBeRemoved)
    {
        /**
         * Function that removes the certain toBoRemoved Dozaj object
         */

        dosageDB.remove(toBeRemoved);

    }

    public ArrayList<Dosage> getAll()
    {
     return dosageDB;
    }
}
