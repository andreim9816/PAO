package Service;
import Main.Main;
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
        Main.auditService.write("Add hospital");
        hospitalRepository.add(h);
        Main.writeFileServiceHospital.appendObject(h, "hospital.csv");
//        Main.hospitalCsvService.writeFile(h);
    }

    public HospitalRepository getHospitalRepository()
    {
        return hospitalRepository;
    }

    public Hospital getHospitalById(int id)
    {
        Main.auditService.write("Get an Hospital by ID");
        return hospitalRepository.getHospitalById(id);
    }

    public boolean hospitalExists(int id)
    {
        if(hospitalRepository.getHospitalById(id) == null)
            return false;
        return true;
    }

    public void printHospital(int id)
    {
        Hospital h = getHospitalById(id);
        if(h == null)
            throw new IllegalArgumentException("This hospital does not exist!");
        System.out.println(h);
    }

    public ArrayList<Hospital> getAllHospital()
    {
        Main.auditService.write("Get all Hospitals");
        return hospitalRepository.getAllHospital();
    }

    public ArrayList<Department> getAllDepartmentsFromHospital(int idHospital)
    {
        Main.auditService.write("Get all Departments from a Hospital");
        ArrayList<Department> departments = DepartmentService.getInstance().getAllDepartment();
        ArrayList<Department> result = new ArrayList<>();

        for(Department department : departments)
            if(department.getIdHospital() == idHospital)
                result.add(department);
        return result;
    }

    public void changeHospitalName(String newName, int id) throws IllegalArgumentException
    {
        AuditService.getInstance().write("Change a hospital's name");
        Hospital h = hospitalRepository.getHospitalById(id);
        if(h == null)
            throw new IllegalArgumentException("This hospital does not exist!");
        else
        {
            System.out.println("Intra si aici");
            h.setName(newName);
            Main.writeFileServiceHospital.updateFile(getAllHospital(), "hospital.csv");
//            HospitalCsvService.getInstance().updateFile();
        }
    }

    public void remove(int idHospital)
    {
        Main.auditService.write("Remove an Hospital");
        Hospital h = instance.getHospitalById(idHospital);
        if(h == null)
            throw new IllegalArgumentException("This hosptial does not exist!");
        else
        {
            ArrayList<Department> departments = instance.getAllDepartmentsFromHospital(idHospital);
            System.out.println("Departamentele sunt: " + departments);
            for(Department department : departments)
                DepartmentService.getInstance().remove(idHospital, department.getNameDepartment());

            hospitalRepository.remove(h);
            Main.writeFileServiceHospital.updateFile(getAllHospital(), "hospital.csv");
//            HospitalCsvService.getInstance().updateFile();
        }
    }
}
