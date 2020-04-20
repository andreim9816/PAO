package Repository;

import Model.personal.Doctor;
import Model.personal.Nurse;
import Model.personal.Patient;
import Model.personal.Person;

import java.util.ArrayList;

public class PersonRepository
{
    private ArrayList<Person> personDB;

    public PersonRepository()
    {
        personDB = new ArrayList<>();
    }

    public void add(Person p)
    {
        personDB.add(p);
    }

    public ArrayList<Person> getAllPatients()
    {
        ArrayList<Person> result = new ArrayList<>();

        for(Person p : personDB)
            if(p instanceof Patient)
                result.add(p);

        return result;
    }

    public ArrayList<Person> getAllDoctors()
    {
        ArrayList<Person> result = new ArrayList<>();

        for(Person p : personDB)
            if(p instanceof Doctor)
                result.add(p);

        return result;
    }

    public ArrayList<Person> getAllNurses()
    {
        ArrayList<Person> result = new ArrayList<>();

        for(Person p : personDB)
            if(p instanceof Nurse)
                result.add(p);
        return result;
    }

    public Person getPersonByCNP(String cnp)
    {

        for(Person p : personDB)
            if( p.getCNP().equals(cnp))
                return p;
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
