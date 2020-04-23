package Model.Utils;
import java.io.BufferedWriter;
import java.io.IOException;

public interface WriteFile<T>
{
    void print(BufferedWriter bufferedWriter) throws IOException;
}
