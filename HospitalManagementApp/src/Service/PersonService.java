package Service;
import Main.Main;
import Model.administrativ.Department;
import Model.administrativ.Hospital;
import Model.personal.Doctor;
import Model.personal.Patient;
import Model.personal.Person;
import Repository.PersonRepository;

import java.util.ArrayList;
import java.util.Vector;

public class PersonService
{
    private PersonRepository personRepository = new PersonRepository();
    private static PersonService instance = new PersonService();

    private PersonService(){}

    public static PersonService getInstance()
    {
        return instance;
    }

    public void add(Person p)
    {
        personRepository.add(p);
    }

    public Person getPersonById(int ID)
    {
        return personRepository.getPersonById(ID);
    }

    public ArrayList<Person> getAllPerson()
    {
        return personRepository.getAllPerson();
    }

    public void remove(Person p)
    {
        personRepository.remove(p);
    }

    public void addPatientToDepartment(Hospital H , Department D , Person p)
    {
        /**
         * Function that adds a specific Patient to a specific Department to a specific Hospital
         */

        personRepository.add(p); // adds Person p in the database


        Hospital hospitalRef = Main.hospitalService.getHospitalReference(H); // gets reference to the Hospital object in the database
        Vector<Department> departments = hospitalRef.getArrayDepartment(); // gets the departments of the Hospital

        int ok = 0;

        for(Department d : departments)
        {
            if (d.equals(D))
                if (p instanceof Patient)
                {
                    d.getArrayPatient().add(p);
                    ok = 1;
                    break;
                }
        }

        if(ok == 0)
            System.out.println("No patient has been added!");
    }

    public void addDoctorToDepartment(Hospital H , Department D , Person p)
    {
        /**
         * Function that adds a specific Doctor to a specific Department to a specific Hospital
         */

        personRepository.add(p); // adds Person p in the database

        Hospital hospitalRef = Main.hospitalService.getHospitalReference(H); // gets reference to the Hospital object in the database
        Vector<Department> departments = hospitalRef.getArrayDepartment(); // gets the departments of the Hospital

        int ok = 0;
        for(Department d : departments)
        {
            if (d.equals(D))
                if (p instanceof Doctor)
                {
                    d.getArrayDoctor().add(p);
                    ok = 1;
                    break;
                }
        }

        if(ok == 0)
            System.out.println("No doctor has been added!");
    }
}
