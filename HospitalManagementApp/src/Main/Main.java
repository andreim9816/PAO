package Main;

import Service.*;
import Model.tratament.*;
import Model.personal.*;
import Model.administrativ.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Main
{
    public static HospitalService hospitalService = HospitalService.getInstance();
    public static DepartmentService departmentService = DepartmentService.getInstance();
    public static PersonService personService = PersonService.getInstance();
    public static MedicationService medicationService = MedicationService.getInstance();
    public static PrescriptionService prescriptionService = PrescriptionService.getInstance();
    public static AddressService addressService = AddressService.getInstance();

    public static AuditService auditService = AuditService.getInstance();
    public static HospitalCsvService hospitalCsvService = HospitalCsvService.getInstance();
    public static DepartmentCsvService departmentCsvService = DepartmentCsvService.getInstance();
    public static PersonCsvService personCsvService = PersonCsvService.getInstance();
    public static PrescriptionCsvService prescriptionCsvService = PrescriptionCsvService.getInstance();

    public static void main(String[] args)
    {
            hospitalCsvService.setFileName("hospital.csv");
            hospitalCsvService.readFile();

            departmentCsvService.setFileName("department.csv");
            departmentCsvService.readFile();

            personCsvService.setFileName("person.csv");
            personCsvService.readFile();

            prescriptionCsvService.setFileName("prescription.csv");
            prescriptionCsvService.readFile();

            Scanner in = new Scanner(System.in);
            int option;

            do {
                System.out.println(
                                    "\n\n" +
                                    "0.Exit \n" +
                                    "1.Add an Hospital  |" +
                                    "2.Change a Hospital's name  |" +
                                    "3.Get an Hospital by its ID \n" +
                                    "4.Get all Hospitals  |" +
                                    "5.Get all Departments from an Hospital  |" +
                                    "6.Remove an Hospital\n" +
                                    "7.Add a Department  |" +
                                    "8.Change a Department's name  |" +
                                    "9.Change a Department's number of beds\n" +
                                    "10.Get a Department  |" +
                                    "11.Get all Departments  |" +
                                    "12.Get all persons from Department\n" +
                                    "13.Remove a Department  |" +
                                    "14.Add Person  |" +
                                    "15.Change a person's last name\n" +
                                    "16.Change a person's first name  |" +
                                    "17.Change a doctor's grade  |" +
                                    "18.Change a patient's insurance status\n" +
                                    "19.Get a person by its CNP  |" +
                                    "20.Get all persons  |" +
                                    "21.Get all doctors\n" +
                                    "22.Get all nurses  |" +
                                    "23.Get all patients  |" +
                                    "24.Get all doctors from department\n" +
                                    "25.Get all patients from department  |" +
                                    "26.Add Prescription  |" +
                                    "27.Change Prescription's Doctor CNP \n" +
                                    "28.Change Prescription's Patient CNP  |" +
                                    "29.Change Prescription's recommendation  |" +
                                    "30.Get Prescription\n" +
                                    "31.Get Medications from Prescription  |" +
                                    "32.Remove Prescription " +
                                    "\n\n"
                );

                System.out.print("Option = ");
                option = in.nextInt();
                in.nextLine();

                switch (option)
                {
                    case 0:
                        break;

                    case 1:
                        System.out.print("Choose a name: ");
                        String name = in.nextLine();
                        hospitalService.addHospital(new Hospital(name));
                        break;

                    case 2:
                        System.out.print("Choose a hospital's ID: ");
                        int ID = in.nextInt();
                        in.nextLine();
                        System.out.print("Choose the new Name: ");
                        name = in.nextLine();

                        try
                        {
                            hospitalService.changeHospitalName(name, ID);
                        }catch (IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 3:
                        System.out.print("Choose a hospital's ID: ");
                        ID = in.nextInt();
                        in.nextLine();
                        try
                        {
                          hospitalService.printHospital(ID);
                        }catch (IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 4:
                        System.out.println(hospitalService.getAllHospital());
                        break;

                    case 5:
                        System.out.print("Choose a hospital's ID: ");
                        ID = in.nextInt();
                        in.nextLine();
                        try
                        {
                            System.out.println(hospitalService.getAllDepartmentsFromHospital(ID));
                        }catch (IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 6:
                        System.out.print("Choose a hospital's ID: ");
                        ID = in.nextInt();
                        in.nextLine();
                        try
                        {
                            hospitalService.remove(ID);
                        }catch (IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 7:
                        System.out.print("Choose a name: ");
                        name = in.nextLine();
                        System.out.print("Choose the number of beds: ");
                        int number = in.nextInt();
                        in.nextLine();
                        System.out.print("Choose the Hospital's Id: ");
                        ID = in.nextInt();
                        in.nextLine();
                        try
                        {
                            departmentService.addDepartment(new Department(name, number), ID);
                        }catch(IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 8:
                        System.out.print("Choose a hospital's ID: ");
                        ID = in.nextInt();
                        in.nextLine();
                        System.out.print("Choose the department's name: ");
                        String oldName = in.nextLine();
                        System.out.print("Choose the new Name: ");
                        String newName = in.nextLine();

                        try
                        {
                            departmentService.changeNameDepartment(newName, ID, oldName);
                        }catch (IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 9:
                        System.out.print("Choose a hospital's ID: ");
                        ID = in.nextInt();
                        in.nextLine();
                        System.out.print("Choose the department's name: ");
                        name = in.nextLine();
                        System.out.print("Choose the new number of beds: ");
                        number = in.nextInt();
                        in.nextLine();

                        try
                        {
                            departmentService.changeNumberOfBeds(number, ID, name);
                        }catch (IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 10:
                        System.out.print("Choose a hospital's ID: ");
                        ID = in.nextInt();
                        in.nextLine();
                        System.out.print("Choose a department's name: ");
                        name = in.nextLine();

                        try
                        {
                            System.out.println(departmentService.getDepartment(ID, name));
                        }
                        catch(IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 11:
                        System.out.println(departmentService.getAllDepartment());
                        break;

                    case 12:
                        System.out.print("Choose a hospital's ID: ");
                        ID = in.nextInt();
                        in.nextLine();
                        System.out.print("Choose the department's name: ");
                        name = in.nextLine();

                        try
                        {
                            System.out.println(departmentService.getAllPersonsFromDepartment(ID, name));
                        }catch(IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 13:
                        System.out.print("Choose a hospital's ID: ");
                        ID = in.nextInt();
                        in.nextLine();
                        System.out.print("Choose the department's name: ");
                        name = in.nextLine();

                        try
                        {
                            departmentService.remove(ID, name);
                        }
                        catch(IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 14:
                        String CNP, lastName, firstName, nameDepartment;
                        int personType;
                        Person person;
                        System.out.print("Choose a CNP: ");
                        CNP =in.nextLine();
                        System.out.print("Choose last name: ");
                        lastName = in.nextLine();
                        System.out.print("Choose first name: ");
                        firstName = in.nextLine();
                        System.out.print("Choose hospital id: ");
                        ID = in.nextInt(); in.nextLine();
                        System.out.print("Choose department name: ");
                        nameDepartment = in.nextLine();
                        System.out.print("Choose type of person: 1.Doctor | 2.Nurse | 3.Patient: ");
                        personType = in.nextInt(); in.nextLine();
                        switch(personType)
                        {
                            case 1:
                                System.out.print("Introduce doctor's grade: ");
                                String grade = in.nextLine();
                                person = new Doctor(lastName, firstName, CNP, grade);
                                try
                                {
                                    personService.add(person, ID, nameDepartment);
                                }
                                catch(IllegalArgumentException e)
                                {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 2:
                                person = new Nurse(lastName, firstName, CNP);
                                try
                                {
                                    personService.add(person, ID, nameDepartment);
                                }
                                catch(IllegalArgumentException e)
                                {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 3:
                                System.out.print("Introduce patient's insurance status: ");
                                boolean insured = in.nextBoolean();
                                System.out.println(insured);

                                person = new Patient(lastName, firstName, CNP, insured);
                                try
                                {
                                    personService.add(person, ID, nameDepartment);
                                }
                                catch(IllegalArgumentException e)
                                {
                                    System.out.println(e.getMessage());
                                }
                                break;
                        }
                        break;

                    case 15:
                        System.out.print("Choose a person's CNP: ");
                        CNP = in.nextLine();
                        System.out.print("Choose the new last name: ");
                        lastName = in.nextLine();
                        try
                        {
                            personService.changeLastName(lastName, CNP);
                        }
                        catch(IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 16:
                        System.out.print("Choose a person's CNP: ");
                        CNP = in.nextLine();
                        System.out.print("Choose the new first name: ");
                        firstName = in.nextLine();
                        try
                        {
                            personService.changeFirstName(firstName, CNP);
                        }
                        catch(IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 17:
                        System.out.print("Choose a doctor's CNP: ");
                        CNP = in.nextLine();
                        System.out.print("Choose the new grade: ");
                        String grade = in.nextLine();
                        try
                        {
                            personService.changeDoctorGrade(grade, CNP);
                        }
                        catch(IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 18:
                        System.out.print("Choose a patient's CNP: ");
                        CNP = in.nextLine();
                        System.out.print("Choose the new insurance status: ");
                        Boolean insured = in.nextBoolean();
                        try
                        {
                            personService.changePatientInsurance(insured, CNP);
                        }
                        catch(IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 19:
                        System.out.print("Choose a persons's CNP: ");
                        CNP = in.nextLine();
                        try
                        {
                            personService.printPerson(CNP);
                        }
                        catch(IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 20:
                        System.out.println(personService.getAllPerson());
                        break;

                    case 21:
                        System.out.println(personService.getAllDoctors());
                        break;

                    case 22:
                        System.out.println(personService.getAllNurses());
                        break;

                    case 23:
                        System.out.println(personService.getAllPatients());
                        break;

                    case 24:
                        System.out.print("Choose a hospital's ID: ");
                        ID = in.nextInt();
                        in.nextLine();
                        System.out.print("Choose the department's name: ");
                        name = in.nextLine();
                        System.out.println(personService.getAllDoctorsFromDepartment(ID, name));

                        break;

                    case 25:
                        System.out.print("Choose a hospital's ID: ");
                        ID = in.nextInt();
                        in.nextLine();
                        System.out.print("Choose the department's name: ");
                        name = in.nextLine();
                        System.out.println(personService.getAllPatientsFromDepartment(ID, name));

                        break;

                    case 26:
                        System.out.print("Choose Doctor's CNP: ");
                        String cnpDoctor = in.nextLine();
                        System.out.print("Choose Patient's CNP: ");
                        String cnpPatient = in.nextLine();
                        System.out.print("Choose recommendation: ");
                        String recommendation = in.nextLine();

                        try
                        {
                            PrescriptionService.getInstance().add(new Prescription(recommendation), cnpPatient, cnpDoctor);
                        }
                        catch(IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 27:
                        System.out.print("Choose Prescription ID: ");
                        ID = in.nextInt(); in.nextLine();
                        System.out.print("Choose doctor's new CNP: ");
                        String newCnpDoctor = in.nextLine();

                        try
                        {
                            prescriptionService.changeCNPDoctor(newCnpDoctor, ID);
                        }
                        catch(IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 28:

                        System.out.print("Choose Prescription ID: ");
                        ID = in.nextInt(); in.nextLine();
                        System.out.print("Choose patient's new CNP: ");
                        String newCnpPatient = in.nextLine();

                        try
                        {
                            prescriptionService.changeCNPPatient(newCnpPatient, ID);
                        }
                        catch(IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 29:
                        System.out.print("Choose Prescription ID: ");
                        ID = in.nextInt(); in.nextLine();
                        System.out.print("Choose new recommendation: ");
                        recommendation = in.nextLine();

                        try
                        {
                            PrescriptionService.getInstance().changeRecommendation(recommendation, ID);
                        }
                        catch(IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 30:
                        System.out.print("Choose Prescription ID: ");
                        ID = in.nextInt(); in.nextLine();

                        try
                        {
                            System.out.println(PrescriptionService.getInstance().getPrescriptionById(ID));
                        }
                        catch(IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 31:
                        System.out.print("Choose Prescription ID: ");
                        ID = in.nextInt(); in.nextLine();

                        try
                        {
                            System.out.println(PrescriptionService.getInstance().getMedicationsFromPrescription(ID));
                        }
                        catch(IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }

                        break;

                    case 32:
                        System.out.print("Choose Prescription ID: ");
                        ID = in.nextInt(); in.nextLine();

                        try
                        {
                            prescriptionService.remove(ID);
                        }
                        catch(IllegalArgumentException e)
                        {
                            System.out.println(e.getMessage());
                        }
                }

            } while (option != 0);
        }

//        ArrayList<Address> addressDB = addressService.getAllAddress();
//        ArrayList<Hospital> hospitalDB = hospitalService.getAllHospital();
//        ArrayList<Department> departmentDB = departmentService.getAllDepartment();
//        ArrayList<Person> personDB = personService.getAllPerson();
//        ArrayList<Prescription> prescriptionDB = prescriptionService.getPrescriptionAll();
//        Set<Medication> medicationDB = medicationService.getAllMedication();
//
//        Address address1 = new Address("Cuza Voda", "Roman", "Neamt", 10);
//        Address address2 = new Address("Tineretului", "Bucuresti", "5", 211);
//
//        Hospital hospital1 = new Hospital("SMU Roman");
//        Hospital hospital2 = new Hospital("Elias");
//
//        try {
//             1) Adds a hospital
//            hospitalService.addHospital(hospital1);
//            hospitalService.addHospital(hospital2);
//
//             2) Adds an address
//            addressService.addAddress(address1, hospital1.getIdHospital());
//            addressService.addAddress(address2, hospital2.getIdHospital());
//
//             3) Changes a field for some specific address
//            addressService.changeCity("Buzau", hospital1.getIdHospital());
//            addressService.changeNumber(10101, hospital2.getIdHospital());
//            addressService.changeStreet("Eternitatii", hospital1.getIdHospital());
//
//             4) Changes a hospital's name
//            hospitalService.changeHospitalName("Noul Nume al spitalului", hospital1.getIdHospital());
//
//            System.out.println(addressDB);
//            System.out.println(hospitalDB);
//
//             5) Adds a department to a specific Hospital, then it changes some fields
//
//            Department department1 = new Department("Cardiologie", 25);
//            Department department2 = new Department("Neurologie", 20);
//            Department department3 = new Department("Chirurgie", 35);
//
//            departmentService.addDepartment(department1, hospital1.getIdHospital());
//            departmentService.addDepartment(department2, hospital1.getIdHospital());
//            departmentService.addDepartment(department3, hospital2.getIdHospital());
//
//            System.out.println(departmentService.getAllDepartment());
//
//            departmentService.remove(hospital2.getIdHospital(), "Chirurgie");
//            System.out.println(departmentService.getAllDepartment());
//
//            departmentService.changeNumberOfBeds(999, hospital1.getIdHospital(), "Cardiologie");
//            departmentService.changeNameDepartment("Radiologie", hospital1.getIdHospital(), "Neurologie");
//
//            System.out.println(departmentDB);
//
//            // 6) Adds Doctors, Nurses and Patients to specific Departments at specific Hospitals
//
//            Person doctor1 = new Doctor("Firastrau", "Victor", "175229272012", "rezident");
//            Person doctor2 = new Doctor("Tubucanu", "Alina", "2801128270844", "primar");
//            Person doctor3 = new Doctor("Palanceanu", "Catalina", "12345678910", "specialist");
//
//            Person nurse1 = new Nurse("Manolache", "Adina", "2740328279900");
//            Person nurse2 = new Nurse("Antici", "Iuliana", "12366233653");
//
//            Person patient1 = new Patient("Petrescu", "Razvan", "216516412993", true);
//            Person patient2 = new Patient("Agavriloaie", "Galia", "6757711238128", false);
//            Person patient3 = new Patient("Martinez", "Sergio", "8251182823509", true);
//            Person patient4 = new Patient("Mocanu", "Miruna", "2981124720922", true);
//            Person patient5 = new Patient("Munteanu", "Alina", "152628192031", false);
//
//            personService.add(doctor1, hospital1.getIdHospital(), "Cardiologie");
//            personService.add(doctor2, hospital1.getIdHospital(), "Radiologie");
//            personService.add(doctor3, hospital2.getIdHospital(), "Chirurgie");
//
//            personService.add(nurse1, hospital1.getIdHospital(), "Cardiologie");
//            personService.add(nurse2, hospital2.getIdHospital(), "Chirurgie");
//
//            personService.add(patient1, hospital1.getIdHospital(), "Cardiologie");
//            personService.add(patient2, hospital1.getIdHospital(), "Cardiologie");
//            personService.add(patient3, hospital1.getIdHospital(), "Cardiologie");
//            personService.add(patient4, hospital1.getIdHospital(), "Radiologie");
//            personService.add(patient5, hospital2.getIdHospital(), "Chirurgie");
//
//            // 7) Changes first name, last name or doctor's grade
//            personService.changeDoctorGrade("primar", doctor1.getCNP());
//            personService.changeFirstName("Cristiana", "2981124720922");
//            personService.changeLastName("Tura", "12366233653");
//
//            // 8) Displays all the doctors from a certain Department in certain Hospital
//
//            System.out.println(personService.getAllDoctorsFromDepartment(hospital1.getIdHospital(), "Cardiologie"));
//            System.out.println(personService.getAllPatientsFromDepartment(hospital1.getIdHospital(), "Cardiologie"));
//            System.out.println(personService.getAllPatientsFromDepartment(hospital2.getIdHospital(), "Chirurgie"));
//
//            // displays all the persons from a Hospital
//            System.out.println("--------------");
//            System.out.println(personService.getAllPersonsFromHospital(hospitalDB.get(0).getIdHospital()));
//
//            // 9) Displays all insured patients from a Department in Hospital
//
//            System.out.println(personService.getAllInsuredPatientsInDepartment(hospitalDB.get(1).getIdHospital(), "Chirurgie"));
//
//            // 10) Adds Prescriptions, changes recommendation or Doctor's CNP
//            Prescription prescription1 = new Prescription("Tratament 30 zile");
//            Prescription prescription2 = new Prescription("In caz de stari de greata, intrerupt tratamentul");
//            Prescription prescription3 = new Prescription("Medicamentele trebuie luate inainte de masa");
//
//            prescriptionService.add(prescription1, patient1.getCNP(), doctor1.getCNP());
//            prescriptionService.add(prescription2, patient4.getCNP(), doctor2.getCNP());
//            prescriptionService.add(prescription3, patient5.getCNP(), doctor3.getCNP());
//
//            prescriptionService.changeRecommendation("Repaus 90 de zile", prescription1.getIdPrescription());
//            prescriptionService.changeCNPDoctor("2981124720922", prescription2.getIdPrescription());
//            System.out.println(prescriptionDB);
//
//            // 11) Adds medication, changes quantity
//            Medication medication1 = new Medication("Paracetamol", 3);
//            Medication medication2 = new Medication("Ibuprofen", 1);
//            Medication medication3 = new Medication("Rivanol", 1);
//            Medication medication4 = new Medication("Prostamol", 2);
//            Medication medication5 = new Medication("Tamiflu", 2);
//            Medication medication6 = new Medication("ACC", 2);
//
//            medicationService.add(medication1, prescription1.getIdPrescription());
//            medicationService.add(medication2, prescription2.getIdPrescription());
//            medicationService.add(medication3, prescription3.getIdPrescription());
//            medicationService.add(medication4, prescription1.getIdPrescription());
//            medicationService.add(medication5, prescription2.getIdPrescription());
//            medicationService.add(medication6, prescription1.getIdPrescription());
//
//            medicationService.changeQuantity(0, medication1.getIdPrescription(), medication1.getNameMedication());
//            medicationService.changeQuantity(5, medication5.getIdPrescription(), medication5.getNameMedication());
//
//            System.out.println("------------------");
//            // 12) Displays all the Medications for a Patient
//            System.out.println("Patient1 medications:\n " + personService.getPatientMedications(patient1));
//
//            // 13) Removes a Medication from A Prescription
//            medicationService.remove(prescription1.getIdPrescription(), medication1.getNameMedication());
//
//            // 14) Removes a Prescription and its Medications
//            prescriptionService.remove(prescription1.getIdPrescription());
//            System.out.println(prescriptionDB);
//
//            // 15) Removes a Person. If it is a Patient, then it removes all its Prescriptions. If it's a Doctor, then it removes just him, without any Prescription or Medications
//            personService.remove(doctor1.getCNP());
//            personService.remove(patient1.getCNP());
//
//            System.out.println(personService.getAllDoctors());
//            System.out.println(medicationService.getAllMedication());
//
//            // 16) Removes a department, along with its Persons, Prescriptions and Medications
//            departmentService.remove(hospital1.getIdHospital(), "Cardiologie");
//
//            System.out.println("\nAfter Cardiologie is removed:\n" + medicationService.getAllMedication());
//
//            // 17) Removes a Hospital, along with all its Departments, Persons, Departments and Medications
//            hospitalService.remove(hospital1.getIdHospital());
//
//            System.out.println("\nAfter Hospital1 was removed:\n" + medicationService.getAllMedication() + "\n" + personService.getAllPerson());
//        }
//        catch(IllegalArgumentException e)
//        {
//            System.out.println(e.getMessage());
//        }
//    }
}
