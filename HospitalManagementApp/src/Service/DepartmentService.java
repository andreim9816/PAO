package Service;

import Main.Main;
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

    public void addDepartment(Department d, int idHospital) throws IllegalArgumentException
    {
        AuditService.getInstance().write("Add department");

        if(HospitalService.getInstance().hospitalExists(idHospital)) // checks if this hospital exists
        {
            d.setIdHospital(idHospital);
            departmentRepository.add(d);
            Main.writeFileService.appendObject(d, "department.csv");
//            Main.writeFileServiceDepartment.appendObject(d, "department.csv");
//            DepartmentCsvService.getInstance().writeFile(d);
        }
        else throw new IllegalArgumentException("Hospital does not exist!");
    }

    public boolean departmentExists(int idHospital, String nameDepartment)
    {
        Department department = getDepartment(idHospital, nameDepartment);
        if(department == null)
            return false;
        return true;
    }

    public DepartmentRepository getDepartmentRepository()
    {
        return departmentRepository;
    }

    public ArrayList<Department> getAllDepartment()
    {
        AuditService.getInstance().write("Get all departments");
        return departmentRepository.getDepartmentAll();
    }

    public Department getDepartment(int idHospital, String nameDepartment)
    {
        AuditService.getInstance().write("Get department");
        Department dep = departmentRepository.getDepartmentReference(idHospital, nameDepartment);
        if(dep == null)
            throw new IllegalArgumentException("No department found!");
        else return dep;
    }

    public void changeNumberOfBeds(int noOfBeds, int idHospital, String nameDepartment)
    {
        AuditService.getInstance().write("Change number of beds");
        Department d = getDepartment(idHospital, nameDepartment);
        if(d == null)
            throw new IllegalArgumentException("This department does not exist!");

        d.setNoOfBeds(noOfBeds);
        Main.writeFileService.updateFile(getAllDepartment(), "department.csv");
//        Main.writeFileServiceDepartment.updateFile(getAllDepartment(), "department.csv");
//        DepartmentCsvService.getInstance().updateFile();
    }

    public void changeNameDepartment(String newName, int idHospital, String nameDepartment)
    {
        AuditService.getInstance().write("Change name of department");
        Department d = getDepartment(idHospital, nameDepartment);
        if(d == null)
            throw new IllegalArgumentException("This department does not exist!");

        d.setNameDepartment(newName);
        Main.writeFileService.updateFile(getAllDepartment(), "department.csv");
//        Main.writeFileServiceDepartment.updateFile(getAllDepartment(), "department.csv");
//        DepartmentCsvService.getInstance().updateFile();
    }

    public ArrayList<Person> getAllPersonsFromDepartment(int idHospital, String nameDepartment)
    {
        AuditService.getInstance().write("Get all persons from department");
        ArrayList<Person> persons = PersonService.getInstance().getAllPerson();
        ArrayList<Person> result = new ArrayList<>();

        for(Person person : persons)
            if(person.getIdHospital() == idHospital && person.getNameDepartment().equals(nameDepartment))
                result.add(person);
        return result;
    }

    public void remove(int idHospital, String departmentName)
    {
        AuditService.getInstance().write("Remove a department");
        Department d = instance.getDepartment(idHospital, departmentName);
        if(d == null)
           throw new IllegalArgumentException("Department does not exist!");
        else
        {
            ArrayList<Person> personsInDepartment = instance.getAllPersonsFromDepartment(idHospital, departmentName);
            for(Person person : personsInDepartment)
                PersonService.getInstance().remove(person.getCNP());

            departmentRepository.remove(d);
            Main.writeFileService.updateFile(getAllDepartment(), "department.csv");
//            Main.writeFileServiceDepartment.updateFile(getAllDepartment(), "department.csv");
//            DepartmentCsvService.getInstance().updateFile();
        }
    }
}
