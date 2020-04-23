package Service;
import Model.Utils.CsvService;
import Model.administrativ.Department;
import java.io.*;
import java.util.ArrayList;

// TO BE DELETED

public class DepartmentCsvService implements CsvService
{
    private String fileName;
    private static DepartmentCsvService instance = new DepartmentCsvService();

    private DepartmentCsvService(){}

    public static DepartmentCsvService getInstance()
    {
        return instance;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String name)
    {
        fileName = new String(name);
    }

    public void addDepartment(Department d, int idHospital)
    {
        d.setIdHospital(idHospital);
        DepartmentService.getInstance().getDepartmentRepository().add(d);
    }

    public void readFile()
    {
        try
        {
            ArrayList<Department> departments = new ArrayList<>();
            File file = new File(fileName);
            if (!file.createNewFile())
            {
                // file already exists
                String line;
                BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

                while ((line = fileReader.readLine()) != null)
                {
                    String[] fields = line.split(",");
                    Department department = new Department(fields[1], Integer.parseInt(fields[2]));
                    addDepartment(department, Integer.parseInt(fields[0])); // adds the hospital
                }
            }
        }catch(IOException e)
        {
            System.out.println("Problem handling the files! Restart the application!");
        }
    }

    public void writeFile(Department department)
    {
        try {
            File file = new File(fileName);

            // creates the file if it does not exist
            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file, true); // append mode = true
            BufferedWriter bufferedWriter = new BufferedWriter(fw);

            bufferedWriter.write(department.getIdHospital() + "," + department.getNameDepartment() + "," + department.getNoOfBeds() + "\r\n");
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch(IOException e)
        {
            System.out.println("Problem handling the files!");
            e.printStackTrace();
        }
    }

    public void updateFile()
    {
        try {
            ArrayList<Department> departments = DepartmentService.getInstance().getAllDepartment();
            File file = new File(fileName);

            // creates the file if it does not exist
            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file, false); // append mode = false (overwrites)
            BufferedWriter bufferedWriter = new BufferedWriter(fw);

            for (Department department : departments)
                bufferedWriter.write(department.getIdHospital() + "," + department.getNameDepartment() + "," + department.getNoOfBeds() + "\r\n");

            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch(IOException e)
        {
            System.out.println("Problem handling the file!");
            e.printStackTrace();
        }
    }
}
