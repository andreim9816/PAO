package Repository;

import Model.administrativ.Address;
import Model.administrativ.Department;
import Model.administrativ.Hospital;
import java.util.ArrayList;

public class HospitalRepository
{
    private ArrayList<Hospital> hospitalDB;

    public HospitalRepository()
    {
        hospitalDB = new ArrayList<>();
    }

    public void add(Hospital h)
    {
        /**
         * Function that adds a speicific hosptial in the database
         */

        hospitalDB.add(h);
    }

    public ArrayList<Hospital> getAllHospital()
    {
        /**
         * Function that returns all the Hospital objects in the database
         */

        return hospitalDB;
    }

    public Hospital getHospitalByAddress(Address adr)
    {
        /**
         * Function that returns the Hosptial object with the specified address
         */

        for(Hospital h : hospitalDB)
            if(h.getAddress().equals(adr))
                return h;

        System.out.println("No object has been found");
        return null;
    }

    public Hospital getHospitalRefernce(Hospital H)
    {
        /**
         * Function that returns a reference to the Hospital object
         */

        for(Hospital h : hospitalDB)
            if(h.equals(H))
                return h;

        System.out.println("No object has been found");
        return null;
    }

    public Department getDepartmentReference(Hospital H , Department D)
    {
        /**
         * Function that returns a reference to a specific Department from a specific Hospital
         */

        for(Hospital h : hospitalDB)
            if(h.equals(H))
                for(Department d : h.getArrayDepartment())
                    if(d.equals(D))
                        return d;

        System.out.println("No department or Hospital found");
        return null;
    }

    public ArrayList<Hospital> getHospitalByName(String name)
    {
        /**
         * Function that returns an array of Hospital objects with a specified name
         */

        ArrayList<Hospital> result = new ArrayList<>();

        for(Hospital h : hospitalDB)
            if(h.getName().equals(name))
                result.add(h);

        return result;

    }
    public void remove(Hospital h)
    {
        /**
         * Function that removes a specific hospital
         */

        hospitalDB.remove(h);
    }


}

