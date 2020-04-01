package Service;

import Model.administrativ.Address;
import Model.administrativ.Department;
import Model.administrativ.Hospital;
import Repository.HospitalRepository;

import java.util.ArrayList;

public class HospitalService
{
    private HospitalRepository hospitalRepository = new HospitalRepository();
    private static HospitalService instance = new HospitalService();

    private HospitalService(){}

    public static HospitalService getInstance()
    {
        return instance;
    }

    public void addHospital(Hospital h)
    {
        // adds a Hospital
        hospitalRepository.add(h);
    }

    public Hospital getHospitalByAddress(Address adr)
    {
        // returns an Hospital, specified by its address
        return hospitalRepository.getHospitalByAddress(adr);
    }

    public void changeHospitalNameByAddress(Address adr , String name)
    {
        // Function that changes a hospital's name
        Hospital h = hospitalRepository.getHospitalByAddress(adr);

        if(h != null)
            h.setName(name);
        else throw new IllegalArgumentException("The hospital doesn't exist");
    }

    public Hospital getHospitalReference(Hospital h)
    {
        return hospitalRepository.getHospitalRefernce(h);
    }

    public ArrayList<Hospital> getAllHospital()
    {
        return hospitalRepository.getAllHospital();
    }

    public void addDepartmentToRepository(Department D)
    {
        ArrayList<Department> depDB = Main.Main.departmentService.getAllDepartment(); // gets the departments from the database
        depDB.add(D);
    }

    public void addDepartmentToHospital(Hospital H , Department D)
    {
        /**
         * Function that adds a specific Department to a specific Hospital
         */

        addDepartmentToRepository(D); // adds department to Repository
        hospitalRepository.getHospitalRefernce(H).getArrayDepartment().add(D);  // adds Department D to the array
    }

    public void changeNoOfBeds(Hospital H , Department D , int numberOfBeds)
    {
        /**
         * Function that changes the number of beds from a specific Department from a specified Hospital
         */

        Hospital h = hospitalRepository.getHospitalRefernce(H); // gets a reference to the Hospital H in the Repository
        Department d = hospitalRepository.getDepartmentReference(h , D); // gets a reference to the Department D in the Repository
        d.setNoOfBeds(numberOfBeds);
    }

    public void remove(Hospital H)
    {
        /**
         * Function that removes a specified Hospital
         */
    }
}