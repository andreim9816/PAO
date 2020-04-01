package Main;

import Model.administrativ.Address;
import Model.administrativ.Department;
import Model.administrativ.Hospital;
import Model.personal.Doctor;
import Model.personal.Patient;
import Model.personal.Person;
import Model.tratament.Dosage;
import Model.tratament.Medication;
import Model.tratament.Prescription;
import Service.*;

import java.util.ArrayList;


public class Main
{

    public static HospitalService hospitalService = HospitalService.getInstance();
    public static DepartmentService departmentService = DepartmentService.getInstance();
    public static PersonService personService = PersonService.getInstance();
    public static MedicationService medicationService = MedicationService.getInstance();
    public static PrescriptionService prescriptionService = PrescriptionService.getInstance();

    public static AddressService addressService = AddressService.getInstance();
    public static DosageService dosageSerivce = DosageService.getInstance();


    public static void main(String[] args)
    {
        Address adr = new Address("Cuza Voda" , "Roman" , "Neamt" , 8);
        Address adr1 = new Address("Tineretului" , "Roman" , "Neamt" , 22);

        // 1) Adds an Hospital
        hospitalService.addHospital(new Hospital(adr, "sf Maria"));
        hospitalService.addHospital(new Hospital(adr1 , "Spitalul de Urgenta"));

        ArrayList<Hospital> hospitals = hospitalService.getAllHospital();

        for(Hospital h : hospitals)
            System.out.println(h);
        System.out.println("\n\n");

        // 2) Changes a Hospital's name
        hospitalService.changeHospitalNameByAddress(adr , "NewNameSet");

        // 3) Adds a Department to a specific Hospital
        Department d = new Department("Cardiology" , 30);
        hospitalService.addDepartmentToHospital(hospitals.get(0) , d);

        for(Hospital h : hospitals)
            System.out.println(h);
        System.out.println("\n\n");

        // 4) Changes the number of beds for a specific department from a specific Hospital

        hospitalService.changeNoOfBeds(hospitals.get(0) , d , 212);

        for(Hospital h : hospitals)
            System.out.println(h);
        System.out.println("\n\n");

        Department d1 = new Department("Neurology" , 20);
        hospitalService.addDepartmentToHospital(hospitals.get(1) , d1);
        hospitalService.addDepartmentToHospital(hospitals.get(1) , new Department("Surgery" , 50));

        for(Hospital h : hospitals)
            System.out.println(h);
        System.out.println("\n\n");

        // 5) Adds a pacient to a specific department, in a specific hospital

        Person patient1 = new Patient("Macovei" , "Denisa" , "9999999999999" , true);
        personService.addPatientToDepartment(hospitals.get(0) , d , patient1);
        personService.changeLastName(patient1 , "Munteanu");

        Person patient2 = new Patient("Dumitrescu" , "Radu" , "12345678910" , false);
        personService.addPatientToDepartment(hospitals.get(1) , d1 , patient2);

        for(Hospital h : hospitals)
            System.out.println(h);
        System.out.println("\n\n");

        // 6) Adds a prescription to a patient

        Prescription prescription2 = new Prescription();
        Prescription prescription1 = new Prescription();

        prescription1.setRecommendation("Stat acasa, baut mult ceai");

        personService.addPrescriptionToPatient(patient1 , prescription1);
        System.out.println(patient1);
        System.out.println("\n\n");

        // 7) Adds a Medication to a Prescription, changes its Dosage

        Dosage dosage1 = new Dosage(3 , "Dupa masa");
        Dosage dosage2 = new Dosage(1 , "Inainte de culcare");

        dosageSerivce.add(dosage1);
        dosageSerivce.add(dosage2);

        Medication medication1 = new Medication("Paracetamol" , dosage1);
        Medication medication2 = new Medication("Ibuprofen" , dosage2);

        prescriptionService.addMedicationToPrescription(prescription1 , medication1);
        prescriptionService.addMedicationToPrescription(prescription1 , medication2);

        dosageSerivce.changeQuantity(dosage1 , 100);
        dosageSerivce.changeSpecs(dosage1 , "Dupa 30 de minute de fiecare masa");

        // 8) Adds a doctor and changes its grade

        Person doctor1 = new Doctor("House" , "Gregory" , "rezident");

        personService.addDoctorToDepartment(hospitals.get(0) , d , doctor1);
        personService.changeFirstName(doctor1 ,  "Mihaita");
        personService.changeDoctorGrade(doctor1 , "primar");

        for(Hospital h : hospitals)
            System.out.println(h);
        System.out.println("\n\n");

        // 9) Checks if a Medication was prescribed to a patient

        System.out.println("Patient " + patient1 + " was prescribed " + medication1 + " " +  personService.isPrescribed(patient1 , medication1));
        System.out.println("Patient " + patient1 + " was prescribed " + medication2 + " " + personService.isPrescribed(patient1 , medication2));
        System.out.println("Patient " + patient2 + " was prescribed " + medication1 + " " + personService.isPrescribed(patient2 , medication1));
        System.out.println("\n\n");

        // 10) Removes a prescription

        prescriptionService.remove(prescription1);
        System.out.println(patient1 + "\n\n");

        // 11) Removes a Person

        personService.remove(patient1.getID());
        System.out.println(personService.getAllPerson());

    }
}
