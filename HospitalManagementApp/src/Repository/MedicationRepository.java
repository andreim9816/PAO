package Repository;
import ComparatorMedication.ComparatorMedication;
import Model.tratament.Medication;
import java.util.Set;
import java.util.TreeSet;

public class MedicationRepository
{
    private Set<Medication> medicationDB;

    public MedicationRepository()
    {
        medicationDB = new TreeSet<>(new ComparatorMedication());
    }

    public void add(Medication m)
    {
        medicationDB.add(m);
    }

    public Set<Medication> getAllMedication()
    {
        return medicationDB;
    }

    public Medication getMedicationByIdPrescriptionAndName(int idPrescription, String nameMedication)
    {
        for(Medication m : medicationDB)
            if(m.getNameMedication().equals(nameMedication) && m.getIdPrescription() == idPrescription)
                return m;
        return null;
    }

    public Set<Medication> getAllMedicament()
    {
        return medicationDB;
    }

    public void remove(Medication m)
    {
         medicationDB.remove(m);
    }
}
