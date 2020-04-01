package Repository;

import Model.administrativ.Department;
import Model.administrativ.Hospital;

import java.util.ArrayList;

public class DepartmentRepository
{
    private ArrayList<Department> departmentDB;

    public DepartmentRepository()
    {
        departmentDB = new ArrayList<>();
    }

    public void add(Department d)
    {
        /**
         * Function that adds a Department in the database
         */
        departmentDB.add(d);
    }

    public ArrayList<Department> getDepartmentAll()
    {
        return departmentDB;
    }

    public ArrayList<Department> getDepartmentByName(String name)
    {
        ArrayList<Department> result = new ArrayList<>();

        for(Department d : departmentDB)
            if(d.getName().equals(name))
                result.add(d);

        return result;
    }

    public Department getDepartmentReference(Department D)
    {
        for(Department d : departmentDB)
            if(d.equals(D))
                return d;
        return null;
    }

    public void remove(Department d)
    {
        /**
         * Function that removes a specific department
         */
        departmentDB.remove(d);
    }
}
