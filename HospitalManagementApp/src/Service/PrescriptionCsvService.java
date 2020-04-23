package Service;

import Model.tratament.Prescription;

import java.io.*;
import java.util.ArrayList;

// TO BE DELETED

public class PrescriptionCsvService
{
    private String fileName;
    private static PrescriptionCsvService instance = new PrescriptionCsvService();

    private PrescriptionCsvService(){}

    public static PrescriptionCsvService getInstance()
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

    public void addPrescription(Prescription prescription, int Id, String CNPDoctor, String CNPPatient)
    {
        prescription.setCNPDoctor(CNPDoctor);
        prescription.setCNPPatient(CNPPatient);
        prescription.setIdPrescription(Id);
        PrescriptionService.getInstance().getPrescriptionRepository().add(prescription);
    }

    public void readFile()
    {
        try {
            File file = new File(fileName);
            if (!file.createNewFile())
            {
                // file already exists
                String line;
                int maxIdPrescription = 0;
                BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

                while ((line = fileReader.readLine()) != null) {
                    String[] fields = line.split(",");

                    Prescription prescription = new Prescription(fields[3]);
                    addPrescription(prescription, Integer.parseInt(fields[0]), fields[1], fields[2]); // adds the prescription

                    if (maxIdPrescription < Integer.parseInt(fields[0]))
                        maxIdPrescription = Integer.parseInt(fields[0]);
                }
                Prescription.setNoOfObjects(maxIdPrescription); // sets the starting id for the new hospitals
            }
        }catch(IOException e)
        {
            System.out.println("Problem handling the files! Restart the application!");
        }
    }

    public void writeFile(Prescription prescription)
    {
        try {
            File file = new File(fileName);

            // creates the file if it does not exist
            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file, true); // append mode = true
            BufferedWriter bufferedWriter = new BufferedWriter(fw);

            bufferedWriter.write(prescription.getIdPrescription() + "," + prescription.getCNPDoctor() + "," + prescription.getCNPPatient() +"," + prescription.getRecommendation() + "\r\n");
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
            ArrayList<Prescription> prescriptions = PrescriptionService.getInstance().getPrescriptionAll();
            File file = new File(fileName);

            // creates the file if it does not exist
            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file, false); // append mode = false (overwrites)
            BufferedWriter bufferedWriter = new BufferedWriter(fw);

            for (Prescription prescription : prescriptions)
                bufferedWriter.write(prescription.getIdPrescription() + "," + prescription.getCNPDoctor() + "," + prescription.getCNPPatient() +"," + prescription.getRecommendation() + "\r\n");

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
