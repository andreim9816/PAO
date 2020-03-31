package Repository;

import Model.personal.Patient;
import Model.personal.Person;
import java.util.ArrayList;

public class PersonRepository
{
    private ArrayList<Person> personDB;

    public PersonRepository()
    {
        /**
         * Constructor
         */
        personDB = new ArrayList<>();
    }

    public void add(Person p)
    {
        /**
         * Function that adds a Person object to the database
         */

        personDB.add(p);
    }

    public ArrayList<Person> insuredPatients()
    {
        /**
         * Function that returns all the insured patients
         */

        ArrayList<Person>result = new ArrayList<>();

        for(Person p : personDB)
            if(p instanceof Patient && ((Patient) p).getInsured())
                result.add(p);

        return result;
    }

    public ArrayList<Person> getAllPatients()
    {
        /**
         * Function that returns the enitre Patient database
         */
        ArrayList<Person> result = new ArrayList<>();

        for(Person p : personDB)
            if(p instanceof Patient)
                result.add(p);

        return result;
    }

    public Person getPatientByCNP(String cnp)
    {
        /**
         * Function that returns a Patient by its CNP
         */

        for(Person p : personDB)
            if(p instanceof Person && ((Patient) p).getCNP().equals(cnp))
                return p;

        System.out.println("No patient found");
        return null;
    }

    public Person getPersonById(int ID)
    {
        /**
         * Function that returns a Person by its ID
         */

        for(Person p : personDB)
            if(p.getID() == ID)
                return p;

        System.out.println("No Person found");
        return null;
    }

    public ArrayList<Person> getAllPerson()
    {
        return personDB;
    }

    public void remove(Person p)
    {
        personDB.remove(p);
    }
}
