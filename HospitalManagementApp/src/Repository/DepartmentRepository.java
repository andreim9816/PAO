package Repository;
import Model.administrativ.Department;
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
        departmentDB.add(d);
    }

    public ArrayList<Department> getDepartmentAll()
    {
        return departmentDB;
    }

    public Department getDepartmentReference(int idHospital, String nameDepartment)
    {
        for(int i = 0 ; i < departmentDB.size() ; i++)
            if(departmentDB.get(i).getIdHospital() == idHospital && departmentDB.get(i).getNameDepartment().equals(nameDepartment))
                return departmentDB.get(i);
        return null;
    }

    public void remove(Department d)
    {
        departmentDB.remove(d);
    }
}
