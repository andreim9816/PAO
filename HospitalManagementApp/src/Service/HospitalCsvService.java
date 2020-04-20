package Service;

import Main.Main;
import Model.administrativ.Hospital;
import java.io.*;
import java.util.ArrayList;

public class HospitalCsvService implements CsvService
{
    private String fileName;
    private static HospitalCsvService instance = new HospitalCsvService();

    private HospitalCsvService(){}

    public static HospitalCsvService getInstance()
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

    public void addHospital(Hospital h)
    {
        HospitalService.getInstance().getHospitalRepository().add(h);
    }

    public void readFile()
    {
        try {
            File file = new File(fileName);
            if (!file.createNewFile())
            {
                // file already exists
                String line;
                int maxIdHospital = 0;
                BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

                while ((line = fileReader.readLine()) != null) {
                    String[] fields = line.split(",");

                    Hospital hospital = new Hospital(Integer.parseInt(fields[0]), fields[1]);
                    addHospital(hospital); // adds the hospital

                    if (maxIdHospital < Integer.parseInt(fields[0]))
                        maxIdHospital = Integer.parseInt(fields[0]);
                }
                Hospital.setNoOfObjects(maxIdHospital); // sets the starting id for the new hospitals
            }
        }catch(IOException e)
        {
            System.out.println("Problem handling the files! Restart the application!");
        }
    }

    public void writeFile(Hospital h)
    {
        try {
            File file = new File(fileName);

            // creates the file if it does not exist
            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file, true); // append mode = true
            BufferedWriter bufferedWriter = new BufferedWriter(fw);

            bufferedWriter.write(h.getIdHospital() + "," + h.getName() + "\r\n");
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
            ArrayList<Hospital> hospitals = Main.hospitalService.getAllHospital();
            File file = new File(fileName);

            // creates the file if it does not exist
            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file, false); // append mode = false (overwrites)
            BufferedWriter bufferedWriter = new BufferedWriter(fw);

            for (Hospital hospital : hospitals)
                bufferedWriter.write(hospital.getIdHospital() + "," + hospital.getName() + "\r\n");

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
