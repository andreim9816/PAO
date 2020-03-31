package Service;

import Model.administrativ.Department;
import Model.administrativ.Hospital;
import Repository.DepartmentRepository;
import Repository.HospitalRepository;

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

    public void remove(Department d)
    {
        departmentRepository.remove(d);
    }

}
