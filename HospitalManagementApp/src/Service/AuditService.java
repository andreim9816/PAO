package Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class AuditService
{
    private static AuditService instance = new AuditService();

    private AuditService(){}

    public static AuditService getInstance()
    {
        return instance;
    }

    public void write(String action)
    {
        try
        {
            File file = new File("audit.csv");

            // creates the file if it does not exist
            if(!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file, true); // append mode = true
            BufferedWriter bufferedWriter = new BufferedWriter(fw);

            // generates the timestamp
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            // writes the file, flushes and closes the file
            bufferedWriter.write(action + "," + timestamp.toString() + "\r\n");
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch(IOException e)
        {
            System.out.println("Problem handling the files!");
            e.printStackTrace();
        }
    }
}
