package Service;
import Model.administrativ.Department;
import Repository.DepartmentRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepartmentService
{
    private DepartmentRepository departmentRepository;
    public static DepartmentService instance = null;

    private DepartmentService(Connection connection)
    {
        departmentRepository = new DepartmentRepository(connection);
    }

    public static DepartmentService getInstance(Connection connection)
    {
        if(instance == null)
            instance = new DepartmentService(connection);
        return instance;
    }

    public void addDepartment(Department d) throws SQLException
    {
        AuditService.getInstance().write(Thread.currentThread().getName() + ", Add department");
            departmentRepository.add(d);
    }

    public ArrayList<Department> getAllDepartment() throws SQLException
    {
        AuditService.getInstance().write(Thread.currentThread().getName() + ", Get all departments");
        return departmentRepository.getDepartmentAll();
    }

    public Department getDepartment(int idHospital, String nameDepartment) throws SQLException
    {
        AuditService.getInstance().write(Thread.currentThread().getName() + ", Get department");
        return departmentRepository.getDepartment(idHospital, nameDepartment);
    }

    public boolean changeNumberOfBeds(int noOfBeds, int idHospital, String nameDepartment) throws SQLException
    {
        AuditService.getInstance().write(Thread.currentThread().getName() + ", Change number of beds");
        return departmentRepository.changeNoOfBeds(idHospital, nameDepartment, noOfBeds);
    }

    public void changeNameDepartment(String newName, int idHospital, String nameDepartment) throws SQLException
    {
        AuditService.getInstance().write(Thread.currentThread().getName() + ", Change name of department");
        departmentRepository.changeDepartmentName(idHospital, nameDepartment, newName);
    }

    public boolean remove(int idHospital, String departmentName) throws SQLException
    {
        AuditService.getInstance().write(Thread.currentThread().getName() + ", Remove a department");
        return departmentRepository.remove(idHospital, departmentName);
    }
}
