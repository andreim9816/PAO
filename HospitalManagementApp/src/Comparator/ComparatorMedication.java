package Comparator;
import Model.tratament.Medication;
import java.util.Comparator;

public class ComparatorMedication implements Comparator<Medication>
{
    @Override
    public int compare(Medication o1, Medication o2)
    {
        return (o1.getNameMedication().compareTo(o2.getNameMedication()));
    }
}
