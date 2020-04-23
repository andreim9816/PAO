package Service;

import Model.Utils.CsvService;
import Model.personal.Doctor;
import Model.personal.Nurse;
import Model.personal.Patient;
import Model.personal.Person;

import java.io.*;
import java.util.ArrayList;

// TO BE DELETED

public class PersonCsvService implements CsvService
{
    private String fileName;
    private static PersonCsvService instance = new PersonCsvService();

    private PersonCsvService(){}

    public static PersonCsvService getInstance()
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

    public void addPerson(Person person, int idHospital, String nameDepartment)
    {
            person.setNameDepartment(nameDepartment);
            person.setIdHospital(idHospital);
            PersonService.getInstance().getPersonRepository().add(person);
    }

    public void readFile()
    {
        try
        {
            File file = new File(fileName);
            if (!file.createNewFile())
            {
                // file already exists
                String line;
                BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

                while ((line = fileReader.readLine()) != null)
                {

                    String[] fields = line.split(",");
                    Person person;
                    if(fields.length == 5) // nurse
                        person = new Nurse(fields[1], fields[2], fields[0]);
                    else
                    {
                        if(fields[5].toLowerCase().equals("False".toLowerCase())
                                || fields[5].toLowerCase().equals("True".toLowerCase())) // patient
                            person = new Patient(fields[1], fields[2], fields[0], Boolean.valueOf(fields[5]));
                        else person = new Doctor(fields[1], fields[2], fields[0], fields[5]); // doctor
                    }
                    addPerson(person, Integer.parseInt(fields[3]), fields[4]);
                }
            }
        }catch(IOException e)
        {
            System.out.println("Problem handling the files! Restart the application!");
        }
    }

    public void writeFile(Person person)
    {
        try {
            File file = new File(fileName);

            // creates the file if it does not exist
            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file, true); // append mode = true
            BufferedWriter bufferedWriter = new BufferedWriter(fw);

            bufferedWriter.write(person.getCNP() + "," + person.getLastName() + "," +  person.getFirstName() + "," + person.getIdHospital() + "," +  person.getNameDepartment());

            if(person instanceof Patient)
            {
                bufferedWriter.write("," + ((Patient) person).getInsured());
            }
            else if(person instanceof Doctor)
                bufferedWriter.write("," + ((Doctor) person).getGrade());

            bufferedWriter.write("\r\n");
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
            ArrayList<Person> persons = PersonService.getInstance().getAllPerson();
            File file = new File(fileName);

            // creates the file if it does not exist
            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file, false); // append mode = false (overwrites)
            BufferedWriter bufferedWriter = new BufferedWriter(fw);

            for (Person person : persons)
            {
                bufferedWriter.write(person.getCNP() + "," + person.getLastName() + "," +  person.getFirstName() + "," + person.getIdHospital() + "," +  person.getNameDepartment());
                if(person instanceof Doctor)
                    bufferedWriter.write("," + ((Doctor) person).getGrade());
                else if(person instanceof Patient)
                    bufferedWriter.write("," + ((Patient) person).getInsured());

                bufferedWriter.write("\r\n");
            }

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
