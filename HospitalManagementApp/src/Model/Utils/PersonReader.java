package Model.Utils;

import Model.personal.Doctor;
import Model.personal.Nurse;
import Model.personal.Patient;
import Model.personal.Person;
import Service.PersonService;

public class PersonReader implements ReadFile
{
    private static PersonReader instance = new PersonReader();
    private PersonReader(){}

    public static PersonReader getInstance()
    {
        return instance;
    }

    public void read(String []fields)
    {
        Person person = generateObj(fields);
        PersonService.getInstance().getPersonRepository().add(person);
    }

    public static Person generateObj(String []fields)
    {
        Person person;

        if(fields.length == 5) // nurse
            person = new Nurse(fields[1], fields[2], fields[0]);

        else if(fields[5].toLowerCase().equals("False".toLowerCase()) || fields[5].toLowerCase().equals("True".toLowerCase())) // patient
                person = new Patient(fields[1], fields[2], fields[0], Boolean.valueOf(fields[5]));

        else person = new Doctor(fields[1], fields[2], fields[0], fields[5]); // doctor

        person.setIdHospital(Integer.parseInt(fields[3]));
        person.setNameDepartment(fields[4]);

        return person;
    }
}
