package Service;
import Comparator.ComparatorMedication;
import Main.Main;
import Model.personal.Doctor;
import Model.personal.Nurse;
import Model.personal.Patient;
import Model.personal.Person;
import Model.tratament.Medication;
import Model.tratament.Prescription;
import Repository.PersonRepository;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class PersonService
{
    private PersonRepository personRepository = new PersonRepository();
    private static PersonService instance = new PersonService();

    private PersonService(){}

    public static PersonService getInstance()
    {
        return instance;
    }

    public PersonRepository getPersonRepository()
    {
        return personRepository;
    }

    public void add(Person p, int idHospital, String nameDepartment) throws IllegalArgumentException
    {
        AuditService.getInstance().write("Add person");
        if(p instanceof Patient)
        {
           int numberOfBeds = Main.departmentService.getDepartment(idHospital, nameDepartment).getNoOfBeds();
           int numberOfPatientsInDepartment = instance.getAllPatientsFromDepartment(idHospital, nameDepartment).size();

           if(numberOfBeds <= numberOfPatientsInDepartment)
               throw new IllegalArgumentException("Maximum number of patients reached! Can't add another Patient");
        }

        if(HospitalService.getInstance().hospitalExists(idHospital))
        {
            if(DepartmentService.getInstance().departmentExists(idHospital, nameDepartment))
            {
                p.setIdHospital(idHospital);
                p.setNameDepartment(nameDepartment);
                personRepository.add(p);
                Main.writeFileService.appendObject(p, "person.csv");
//                Main.writeFileServicePerson.appendObject(p, "person.csv");
//                PersonCsvService.getInstance().writeFile(p);
            }
            else throw new IllegalArgumentException("Department does not exist!");
        }
        else throw new IllegalArgumentException("Hospital does not exist!");
    }

    public Person getPersonByCNP(String CNP) throws IllegalArgumentException
    {
        AuditService.getInstance().write("Get person by CNP");
        Person p = personRepository.getPersonByCNP(CNP);
        if(p == null)
            throw new IllegalArgumentException("No Person found!");
        else return p;
    }

    public boolean isDoctor(String CNP)
    {
        Person p = getPersonByCNP(CNP);
        if(p instanceof Doctor)
            return true;
        return false;
    }

    public boolean isPatient(String CNP)
    {
        Person p = getPersonByCNP(CNP);
        if(p instanceof Patient)
            return true;
        return  false;
    }

    public void printPerson(String CNP)
    {
        Person p = personRepository.getPersonByCNP(CNP);
        if(p == null)
            throw new IllegalArgumentException("No Person found!");
        else System.out.println(p);
    }

    public ArrayList<Person> getAllPerson()
    {
        AuditService.getInstance().write("Get all persons");
        return personRepository.getAllPerson();
    }

    public ArrayList<Person> getAllPatients()
    {
        AuditService.getInstance().write("Get all patients");
        return personRepository.getAllPatients();
    }

    public ArrayList<Person> getAllDoctors()
    {
        AuditService.getInstance().write("Get all doctors");
        return personRepository.getAllDoctors();
    }

    public ArrayList<Person> getAllNurses()
    {
        AuditService.getInstance().write("Get all nurses");
        return personRepository.getAllNurses();
    }

    public void changeFirstName(String newName, String CNP) throws IllegalArgumentException
    {
        AuditService.getInstance().write("Change first name");
        Person p = getPersonByCNP(CNP);
        if(p == null)
            throw new IllegalArgumentException("No person found!");
        p.setFirstName(newName);
        Main.writeFileService.updateFile(getAllPerson(), "person.csv");
//        Main.writeFileServicePerson.updateFile(getAllPerson(), "person.csv");
//        PersonCsvService.getInstance().updateFile();
    }

    public void changeLastName(String newName, String CNP) throws IllegalArgumentException
    {
        AuditService.getInstance().write("Change last name");
        Person p = getPersonByCNP(CNP);
        if(p == null)
            throw new IllegalArgumentException("No person found!");
        p.setLastName(newName);
        Main.writeFileService.updateFile(getAllPerson(), "person.csv");
//        Main.writeFileServicePerson.updateFile(getAllPerson(), "person.csv");
//        PersonCsvService.getInstance().updateFile();
    }

    public void changeDoctorGrade(String grade, String CNP) throws IllegalArgumentException
    {
        AuditService.getInstance().write("Change doctor grade");
        Person p = getPersonByCNP(CNP);
        if(p == null)
            throw new IllegalArgumentException("No person found!");

        if(p instanceof Doctor)
        {
            ((Doctor) p).setGrade(grade);
        }
        else throw new IllegalArgumentException("Person is not a doctor!");
        Main.writeFileService.updateFile(getAllPerson(), "person.csv");

//        Main.writeFileServicePerson.updateFile(getAllPerson(), "person.csv");
//        PersonCsvService.getInstance().updateFile();
    }

    public void changePatientInsurance(Boolean insured, String CNP) throws IllegalArgumentException
    {
        AuditService.getInstance().write("Change patient insurance status");
        Person p = getPersonByCNP(CNP);
        if(p == null)
            throw new IllegalArgumentException("No person found!");
        if(p instanceof Patient)
            ((Patient) p).setInsured(insured);
        else throw new IllegalArgumentException("Person not a patient!");
        Main.writeFileService.updateFile(getAllPerson(), "person.csv");

//        Main.writeFileServicePerson.updateFile(getAllPerson(), "person.csv");
//        PersonCsvService.getInstance().updateFile();
    }

    public ArrayList<Person> getAllDoctorsFromDepartment(int idHospital, String nameDepartment)
    {
        AuditService.getInstance().write("Get all doctors from department");
        ArrayList<Person> doctors = getAllDoctors();
        ArrayList<Person> result = new ArrayList<>();

        for(Person p : doctors)
            if(p.getNameDepartment().equals(nameDepartment) && p.getIdHospital() == idHospital)
                result.add(p);
        return result;
    }

    public ArrayList<Person> getAllPatientsFromDepartment(int idHospital, String nameDepartment)
    {
        AuditService.getInstance().write("Get all patients from department");
        ArrayList<Person> patients = getAllPatients();
        ArrayList<Person> result = new ArrayList<>();

        for(Person p : patients)
            if(p.getNameDepartment().equals(nameDepartment) && p.getIdHospital() == idHospital)
                result.add(p);
        return result;
    }

    public ArrayList<Person> getAllPersonsFromHospital(int idHospital)
    {
        AuditService.getInstance().write("Get all persons from hospital");
        ArrayList<Person> persons = getAllPerson();
        ArrayList<Person> result = new ArrayList<>();

        for(Person p : persons)
            if(p.getIdHospital() == idHospital)
                result.add(p);
        return result;
    }

    public ArrayList<Person> getAllInsuredPatientsInDepartment(int idHospital, String nameDepartment)
    {
        AuditService.getInstance().write("Get all insured patients from department");
        ArrayList<Person> patients = getAllPatients();
        ArrayList<Person> result = new ArrayList<>();

        for(Person p : patients)
            if(p.getNameDepartment().equals(nameDepartment) && p.getIdHospital() == idHospital)
                if(p instanceof Patient)
                    if(((Patient) p).getInsured() == true)
                       result.add(p);
        return result;
    }

    public Set<Medication> getPatientMedications(Person patient)
    {
        AuditService.getInstance().write("Get all patients from medications");
        if(patient instanceof Patient)
        {
            ArrayList<Prescription> prescriptions = PersonService.getInstance().getPatientPrescriptions(patient);
            Set<Medication> result = new TreeSet<>(new ComparatorMedication());

            for (Prescription prescription : prescriptions)
                {
                    Set<Medication> medications = PrescriptionService.getInstance().getAllMedications(prescription);
                    for (Medication m : medications)
                        result.add(m);
                }
            return result;
        }
        else throw new IllegalArgumentException("Person not a patient!");
    }

    public ArrayList<Prescription> getPatientPrescriptions(Person patient)
    {
        AuditService.getInstance().write("Get patient's prescriptions");
        if(patient instanceof Patient)
        {
            ArrayList<Prescription> prescriptions = PrescriptionService.getInstance().getPrescriptionAll();
            ArrayList<Prescription> result = new ArrayList<>();

            for(Prescription p : prescriptions)
                if(p.getCNPPatient().equals(patient.getCNP()))
                    result.add(p);
            return result;
        }
        else throw new IllegalArgumentException("Person not a patient!");
    }

    public void remove(String CNP)
    {
        AuditService.getInstance().write("Remove person");
        Person p = getPersonByCNP(CNP);
        if(p == null)
           throw new IllegalArgumentException("Person with CNP: " + CNP + " does not exist!");
        else
            if(p instanceof Doctor || p instanceof Nurse)
                personRepository.remove(p);
            else
            {
                ArrayList<Prescription> prescriptions = getPatientPrescriptions(p);
                for(Prescription prescription : prescriptions)
                    Main.prescriptionService.remove(prescription.getIdPrescription());

                personRepository.remove(p);
            }
        Main.writeFileService.updateFile(getAllPerson(), "person.csv");
//        Main.writeFileServicePerson.updateFile(getAllPerson(), "person.csv");
//        PersonCsvService.getInstance().updateFile();
    }
}
