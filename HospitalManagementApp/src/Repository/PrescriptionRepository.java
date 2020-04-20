package Repository;
import ComparatorMedication.ComparatorMedication;
import Main.Main;
import Model.tratament.Medication;
import Model.tratament.Prescription;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class PrescriptionRepository
{
    private ArrayList<Prescription> prescriptionDB;

    public PrescriptionRepository()
    {
        prescriptionDB = new ArrayList<>();
    }

    public ArrayList<Prescription> getPrescriptionAll()
    {
        return prescriptionDB;
    }

    public void add(Prescription r)
    {
        prescriptionDB.add(r);
    }

    public Prescription getPrescriptionById(int idPrescription)
    {
        for(Prescription p : prescriptionDB)
            if(p.getIdPrescription() == idPrescription)
                return p;
        return null;
    }

    public Set<Medication> getMedicationsFromPrescription(int prescriptionId)
    {
        Set<Medication> result = new TreeSet<Medication>(new ComparatorMedication());

        for(Medication m : Main.medicationService.getAllMedication())
            if(m.getIdPrescription() == prescriptionId)
                result.add(m);
        return result;
    }

    public void removeById(int ID)
    {
        boolean isRemoved = false;
        for(Prescription r : prescriptionDB)
            if(r.getIdPrescription() == ID)
            {
                prescriptionDB.remove(r);
                isRemoved = true;
                break;
            }

        if(isRemoved == false)
            System.out.println("No Prescription object was removed because it doesn't exist");
    }
}
