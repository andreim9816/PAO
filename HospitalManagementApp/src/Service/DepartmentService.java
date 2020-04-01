package Service;

import Model.administrativ.Department;
import Model.personal.Person;
import Repository.DepartmentRepository;

import java.util.ArrayList;

public class DepartmentService
{
    private DepartmentRepository departmentRepository = new DepartmentRepository();
    private static DepartmentService instance = new DepartmentService();

    private DepartmentService() {}

    public static DepartmentService getInstance()
    {
        return instance;
    }

    public ArrayList<Department> getAllDepartment()
    {
        return departmentRepository.getDepartmentAll();
    }

    public Department getDepartmentReference(Department D)
    {
        return departmentRepository.getDepartmentReference(D);
    }

    public boolean personInDepartment(Department D , int Id)
    {
        /**
         * Function that checks if a person (given by an ID) is part of a Department
         */

            for(Person p : D.getArrayPatient())
                if(p.getID() == Id)
                    return true;

            for(Person p : D.getArrayDoctor())
                if(p.getID() == Id)
                    return true;
            return false;
    }

    public Department getDepartment(int ID)
    {
        /**
         * Function that returns the department a person is part of
         */

        for(Department d : departmentRepository.getDepartmentAll())
        {
            if(personInDepartment(d , ID))
                return d;
        }

        System.out.println("Person not found in any department");
        return null;
    }

    public void remove(Department d)
    {
        departmentRepository.remove(d);
    }

}
