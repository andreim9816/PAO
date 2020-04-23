package Service;

import Model.Utils.ReadFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileService
{
    private static ReadFileService instance = new ReadFileService();

    private ReadFileService(){}

    public static ReadFileService getInstance()
    {
        return instance;
    }

    public void readFile(ReadFile readFile, String fileName)
    {
        try {
            File file = new File(fileName);
            if (!file.createNewFile())
            {
                // file already exists
                String line;
                BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

                while ((line = fileReader.readLine()) != null)
                {
                    String[] fields = line.split(",");
                    readFile.read(fields);
                }
                fileReader.close();
            }
        }catch(IOException e)
        {
            System.out.println("Problem handling the files! Restart the application!");
        }
    }
}
