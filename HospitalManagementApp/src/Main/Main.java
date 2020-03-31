package Main;

import Model.administrativ.Address;
import Model.administrativ.Department;
import Model.administrativ.Hospital;
import Model.personal.Doctor;
import Model.personal.Patient;
import Model.personal.Person;
import Model.tratament.Dosage;
import Model.tratament.Prescription;
import Service.DepartmentService;
import Service.HospitalService;
import Service.PersonService;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main
{

    public static HospitalService hospitalService = HospitalService.getInstance();
    public static DepartmentService departmentService = DepartmentService.getInstance();
    public static PersonService personService = PersonService.getInstance();

    /**
    public static AddressService addressService = AddressService.getInstance();
    public static DoctorService doctorService = DectorService.getInstance();
    public static PatientService patientService = PatientService.getInstance();
    public static MedicationService medicationService = MedicationService.getInstance();
    public static PrescriptionService prescriptionService = PrescriptionService.getInstance();
    public static DosageSerivce dosageSerivce = DosageService.getInstance();
    */

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

        for(Hospital h : hospitals)
            System.out.println(h);
        System.out.println("\n\n");
    }
}
