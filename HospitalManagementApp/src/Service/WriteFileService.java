package Service;

import Model.Utils.WriteFile;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteFileService<T extends WriteFile>
{
    private static WriteFileService instance = new WriteFileService();

    private WriteFileService(){}

    public static WriteFileService getInstance()
    {
        return instance;
    }

    public void appendObject(T object, String fileName)
    {
        try {
            File file = new File(fileName);

            // creates the file if it does not exist
            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file, true); // append mode = true
            BufferedWriter bufferedWriter = new BufferedWriter(fw);
            object.print(bufferedWriter);
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch(IOException e)
        {
            System.out.println("Problem handling the files!");
            e.printStackTrace();
        }
    }

    public void updateFile(ArrayList<T> array, String fileName)
    {
        try {
            File file = new File(fileName);

            // creates the file if it does not exist
            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file, false); // append mode = false (overwrites)
            BufferedWriter bufferedWriter = new BufferedWriter(fw);

            for (T object : array)
                object.print(bufferedWriter);

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
