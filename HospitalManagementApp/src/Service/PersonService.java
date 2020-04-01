package Service;
import Main.Main;
import Model.administrativ.Department;
import Model.administrativ.Hospital;
import Model.personal.Doctor;
import Model.personal.Patient;
import Model.personal.Person;
import Model.tratament.Medication;
import Model.tratament.Prescription;
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

    public void changeFirstName(Person P , String name)
    {
        Person getRef = personRepository.getPersonReference(P);
        getRef.setFirstName(name);
    }

    public void changeLastName(Person P , String name)
    {
        Person getRef = personRepository.getPersonReference(P);
        getRef.setLastName(name);
    }

    public void changeDoctorGrade(Person P , String grade)
    {
        /**
         * Function that changes a doctor's grade
         */

        if(P instanceof Doctor)
        {
            ((Doctor) P).setGrade(grade);
        }
        else System.out.println("Person is not a doctor");
    }

    public void remove(int id)
    {
        Department department = Main.departmentService.getDepartment(id); // the department where the person is

        for(Person p : department.getArrayPatient())
            if(p.getID() == id)
                {
                    department.getArrayPatient().remove(p);
                    personRepository.remove(p);
                    break;
                }

        for(Person p : department.getArrayDoctor())
            if(p.getID() == id) {
                department.getArrayDoctor().remove(p);
                personRepository.remove(p);
                break;
            }
    }

    public void addPatientToDepartment(Department D , Person p)
    {
        /**
         * Function that adds a specific Patient to a specific Departmeny
         */

        personRepository.add(p);

    }

    public void addPatientToDepartment(Hospital H , Department D , Person p)
    {
        /**
         * Function that adds a specific Patient to a specific Department to a specific Hospital
         */

        personRepository.add(p);

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

        personRepository.add(p);

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

    public boolean isPrescribed(Person P , Medication M)
    {
        /**
         * Function that checks if a Patient was prescribed a Medication
         */

        if(P instanceof  Patient)
        {
            Vector<Medication> arrayMedication =  ((Patient) P).getPrescription().getArrayMedication();
            for(Medication m : arrayMedication)
                if(m.equals(M))
                    return true;
            return false;
        }
        else System.out.println("Person is not a patient");
        return false;
    }

    public void addPrescriptionToPatient(Person P , Prescription prescription)
    {
        /**
         * Funcion that adds a Prescription to a Patient
         */

        Person persRef = personRepository.getPersonReference(P);
        if(persRef instanceof Patient)
        {
            ((Patient) persRef).setPrescription(prescription);
            prescription.setPatientFirstName(persRef.getFirstName());
            prescription.setPatientLastName(persRef.getlastName());
            Main.prescriptionService.getPrescriptionAll().add(prescription);
        }
        else System.out.println("No prescription added, person is not a patient");

    }
}
