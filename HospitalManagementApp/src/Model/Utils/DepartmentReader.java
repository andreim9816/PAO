package Model.Utils;

import Model.administrativ.Department;
import Service.DepartmentService;

public class DepartmentReader implements ReadFile
{
    private static DepartmentReader instance = new DepartmentReader();
    private DepartmentReader(){}

    public static DepartmentReader getInstance()
    {
        return instance;
    }

    public void read(String []fields)
    {
        Department department = generateObj(fields);
        DepartmentService.getInstance().getDepartmentRepository().add(department);
    }

    public static Department generateObj(String []fields)
    {
        Department d =  new Department(fields[1], Integer.parseInt(fields[2]));
        d.setIdHospital(Integer.parseInt(fields[0]));
        return d;
    }
}
