package Service;

import Model.tratament.Dosage;
import Model.tratament.Medication;
import Repository.MedicationRepository;

public class MedicationService
{
    private MedicationRepository medicationRepository = new MedicationRepository();
    public static MedicationService instance = new MedicationService();

    private MedicationService(){}

    public static MedicationService getInstance()
    {
        return instance;
    }

    public void add(Medication m)
    {
        medicationRepository.add(m);
    }

    public Medication getMedicationReference(Medication M)
    {
        return medicationRepository.getMedicationReference(M);
    }

    public void changeDosage(Medication M , Dosage D)
    {
        medicationRepository.getMedicationReference(M).setdosage(D);
    }

    public void changeDosage(Medication M , String name)
    {
        medicationRepository.getMedicationReference(M).setName(name);
    }

    public void remove(Medication m)
    {
        medicationRepository.remove(m);
    }


}
